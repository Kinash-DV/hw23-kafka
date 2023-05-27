package dv.kinash.kafka.producers.wiki;

import com.fasterxml.jackson.core.JsonProcessingException;
import dv.kinash.kafka.model.News;
import dv.kinash.kafka.model.User;
import dv.kinash.kafka.model.converter.NewsJsonConverter;
import dv.kinash.kafka.model.converter.UserJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    @Autowired
    private NewsJsonConverter newsConverter;
    @Autowired
    private UserJsonConverter userConverter;
    @Autowired
    private KafkaTemplate<String, String> kafkaNews;
    @Autowired
    private KafkaTemplate<String, String> kafkaUsers;

    public void sendNews(News news) {
        try {
            kafkaNews.send("news", newsConverter.objectToString(news));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendUser(User user) {
        try {
            kafkaUsers.send("users", userConverter.objectToString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
