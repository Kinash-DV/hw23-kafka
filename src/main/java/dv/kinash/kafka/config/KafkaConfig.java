package dv.kinash.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newsTopic() {
        return TopicBuilder.name("news").build();
    }
    @Bean
    public NewTopic usersTopic() {
        return TopicBuilder.name("users").build();
    }

}
