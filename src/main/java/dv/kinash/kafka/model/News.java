package dv.kinash.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Long id;
    private String title;
    private String text;
    private User user;
    private Source source;
}
