package br.com.post.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
