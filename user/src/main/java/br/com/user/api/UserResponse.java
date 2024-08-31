package br.com.user.api;

import br.com.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private UserEntity.Type type;

    public static UserResponse of(UserEntity user) {
        return new UserResponse(
                user.id(),
                user.email(),
                user.name(),
                user.type()
        );
    }
}
