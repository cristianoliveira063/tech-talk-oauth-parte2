package br.com.post.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private UserType type;

}
