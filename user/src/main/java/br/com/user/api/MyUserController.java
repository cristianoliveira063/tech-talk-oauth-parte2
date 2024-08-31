package br.com.user.api;

import br.com.user.domain.UserEntity;
import br.com.user.domain.UserRepository;
import br.com.user.security.CanEditMyUser;
import br.com.user.security.CanReadMyUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class MyUserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @CanReadMyUser
    @GetMapping
    public UserResponse me(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getClaims().get("sub").toString();

        return userRepository.findByEmail(email)
                .map(UserResponse::of)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    @CanEditMyUser
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal Jwt jwt,
                       @RequestBody MyUserUpdateRequest request) {
        String email = jwt.getClaims().get("sub").toString();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        request.update(user);
        userRepository.save(user);
    }

    @CanEditMyUser
    @PutMapping("/update-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@AuthenticationPrincipal Jwt jwt,
                               @RequestBody MyUserUpdatePasswordRequest request) {
        String email = jwt.getClaims().get("sub").toString();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
        user.password(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid MyUserRegisterRequest request) {
        UserEntity user = request.toEntity();
        user.password(passwordEncoder.encode(request.getPassword()));
        user = this.userRepository.save(user);
        return UserResponse.of(user);
    }

}
