package br.com.user.config;

import br.com.user.domain.UserEntity;
import br.com.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log
@RequiredArgsConstructor
public class FirstUserConfig implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() != 0) {
            return;
        }

        log.info("Nenhum usuário encontrado, cadastrando usuários padrão.");

        userRepository.save(new UserEntity().name("Alex Silva").email("admin@email.com")
                .password(passwordEncoder.encode("123456")).type(UserEntity.Type.ADMIN));


        userRepository.save(new UserEntity().name("João da Silva").email("joao@email.com")
                .password(passwordEncoder.encode("123456")).type(UserEntity.Type.CLIENT));

    }
}
