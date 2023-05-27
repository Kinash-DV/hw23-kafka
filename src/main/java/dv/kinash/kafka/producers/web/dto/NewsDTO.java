package dv.kinash.kafka.producers.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsDTO {
    private Long id;
    private String title;
    private String text;
    private Long user_id;
    private String user_name;
}
