package br.com.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@Entity
@Table(name = "user")
@Data
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {

    private static final String SEQUENCE = "user_seq";

    @Id
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Type type;
    @CreatedDate
    private OffsetDateTime createdAt;

    public enum Type {
        ADMIN, CLIENT;
    }

}