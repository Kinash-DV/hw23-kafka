package dv.kinash.kafka.consumers.mssql.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "news")
public class NewsEntity {
    @Id
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String source;
}
