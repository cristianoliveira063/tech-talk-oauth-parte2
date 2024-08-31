package br.com.user.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyUserUpdatePasswordRequest {

    @NotBlank
    private String password;

}
