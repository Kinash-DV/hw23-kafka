package dv.kinash.kafka.consumers.mongo.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collation = "news")
public class NewsDocument {
    @Id
    private Long id;
    private Long userId;
    private String title;
    private String text;
    private String source;
}
