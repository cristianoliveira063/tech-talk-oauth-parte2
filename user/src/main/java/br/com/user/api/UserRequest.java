package br.com.user.api;

import br.com.user.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private UserEntity.Type type;


    public UserEntity toEntity() {
        return new UserEntity()
                .name(this.name)
                .email(this.email)
                .password(new BCryptPasswordEncoder().encode(this.password))
                .type(this.type);

    }
}
