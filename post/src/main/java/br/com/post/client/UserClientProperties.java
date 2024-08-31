package br.com.post.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("tech.talk.user-api")
@Component
@Getter
@Setter
public class UserClientProperties {
    private String url;
    private String encodedCredentials;


}
