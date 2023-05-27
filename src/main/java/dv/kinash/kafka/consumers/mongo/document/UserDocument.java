package dv.kinash.kafka.consumers.mongo.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collation = "users")
public class UserDocument {
    @Id
    private Long id;
    private String name;
    private String source;
}
