package dv.kinash.kafka.consumers.mssql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private String source;
}
