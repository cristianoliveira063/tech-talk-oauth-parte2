package br.com.post.api;

import br.com.post.client.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EditorResponse {
    private Long id;
    private String name;

    public static EditorResponse of(UserResponse editor) {
        return new EditorResponse(editor.getId(), editor.getName());
    }

    public static EditorResponse anonymousEditor(Long id) {
        return new EditorResponse(id, "Anônimo");
    }
}
