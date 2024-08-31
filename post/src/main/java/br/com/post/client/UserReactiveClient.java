package br.com.post.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserReactiveClient {

    private final WebClient webClient;
    private final UserClientProperties userClientProperties;


    public Mono<UserResponse> findById(Long id) {
        return webClient.get()
                .uri(userClientProperties.getUrl() + "/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }
}
