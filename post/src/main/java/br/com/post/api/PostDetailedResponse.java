package br.com.post.api;

import br.com.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

import static br.com.post.api.EditorResponse.anonymousEditor;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDetailedResponse {
    private Long id;
    private OffsetDateTime createdAt;
    private String title;
    private EditorResponse editor;
    private String content;

    public static PostDetailedResponse of(Post post) {
        return new PostDetailedResponse(
                post.id(),
                post.createdAt(),
                post.title(),
                anonymousEditor(post.editorId()),
                post.content());
    }

    public static PostDetailedResponse of(Post post, EditorResponse editor) {
        return new PostDetailedResponse(
                post.id(),
                post.createdAt(),
                post.title(),
                editor,
                post.content());
    }

}
