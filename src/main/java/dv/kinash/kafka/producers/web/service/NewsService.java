package dv.kinash.kafka.producers.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dv.kinash.kafka.model.News;
import dv.kinash.kafka.model.Source;
import dv.kinash.kafka.model.User;
import dv.kinash.kafka.model.converter.NewsJsonConverter;
import dv.kinash.kafka.model.converter.UserJsonConverter;
import dv.kinash.kafka.producers.web.dto.NewsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private NewsJsonConverter newsConverter;
    @Autowired
    private UserJsonConverter userConverter;
    @Autowired
    private KafkaTemplate<String, String> kafkaNews;
    @Autowired
    private KafkaTemplate<String, String> kafkaUsers;

    public void sendNews(NewsDTO newsDTO) throws JsonProcessingException {
        News news = mapper.map(newsDTO, News.class);
        User user = mapper.map(newsDTO, User.class);
        news.setUser(user);
        news.setSource(Source.rest);
        user.setSource(Source.rest);

        kafkaNews.send("news", newsConverter.objectToString(news));
        kafkaUsers.send("users", userConverter.objectToString(user));
    }

}
