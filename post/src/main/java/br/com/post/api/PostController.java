package br.com.post.api;


import br.com.post.client.UserReactiveClient;
import br.com.post.domain.Post;
import br.com.post.domain.PostRepository;
import br.com.post.security.CanWritePosts;
import br.com.post.security.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final SecurityService securityService;
    private final UserReactiveClient userClient;


    @GetMapping
    public Page<PostSummaryResponse> findAll(Pageable pageable) {
        final Page<Post> postPage = postRepository.findAll(pageable);
        final var postResponses = postPage.getContent()
                .stream()
                .map(PostSummaryResponse::of)
                .collect(Collectors.toList());
        return new PageImpl<>(postResponses, pageable, postPage.getTotalElements());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CanWritePosts
    public PostDetailedResponse create(@RequestBody @Valid PostRequest postRequest) {
        final Post post = new Post()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .editorId(securityService.getUserId());

        postRepository.save(post);
        return userClient.findById(post.editorId())
                .map(userResponse -> PostDetailedResponse.of(post, EditorResponse.of(userResponse)))
                .blockOptional()
                .orElseGet(() -> PostDetailedResponse.of(post));
    }

    @GetMapping("/{id}")
    public PostDetailedResponse findById(@PathVariable Long id) {
        final Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return userClient.findById(post.editorId())
                .map(userResponse -> PostDetailedResponse.of(post, EditorResponse.of(userResponse)))
                .blockOptional()
                .orElseGet(() -> PostDetailedResponse.of(post));
    }
}
