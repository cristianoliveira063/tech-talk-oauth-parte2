package br.com.user.api;

import br.com.user.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private UserEntity.Type type;


    public void update(UserEntity currentUser) {
        currentUser.email(this.email);
        currentUser.name(this.name);
        currentUser.type(this.type);
    }
}
