package br.com.post.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@Entity
@Table(name = "post")
@Data
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    private static final String SEQUENCE = "post_seq";

    @Id
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @jakarta.persistence.GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    @CreatedDate
    private OffsetDateTime createdAt;
    private Long editorId;
    private String title;
    private String content;

}
