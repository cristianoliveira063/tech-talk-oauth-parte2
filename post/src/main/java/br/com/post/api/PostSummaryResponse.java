package br.com.post.api;

import br.com.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PostSummaryResponse {
    private Long id;
    private String title;

    public static PostSummaryResponse of(Post post) {

        return new PostSummaryResponse(post.id(), post.title());
    }

}
