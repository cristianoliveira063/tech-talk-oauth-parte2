package br.com.post.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Log
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;
    private final UserClientProperties properties;

    public Optional<UserResponse> findById(Long id) {
        try {
            final String url = properties.getUrl() + "/users/{id}";
            final UserResponse response = restTemplate.getForObject(url, UserResponse.class, id);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
