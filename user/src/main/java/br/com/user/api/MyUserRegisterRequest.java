package br.com.user.api;

import br.com.user.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyUserRegisterRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public UserEntity toEntity() {
        return new UserEntity()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .type(UserEntity.Type.CLIENT);

    }
}
