package dv.kinash.kafka.consumers.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import dv.kinash.kafka.consumers.mongo.document.NewsDocument;
import dv.kinash.kafka.consumers.mongo.document.UserDocument;
import dv.kinash.kafka.consumers.mongo.repository.NewsDocRepository;
import dv.kinash.kafka.consumers.mongo.repository.UsersDocRepository;
import dv.kinash.kafka.model.News;
import dv.kinash.kafka.model.User;
import dv.kinash.kafka.model.converter.NewsJsonConverter;
import dv.kinash.kafka.model.converter.UserJsonConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMongoService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private NewsJsonConverter newsConverter;
    @Autowired
    private UserJsonConverter userConverter;
    @Autowired
    private NewsDocRepository newsRepository;
    @Autowired
    private UsersDocRepository usersRepository;

    @KafkaListener(topics = "news", groupId = "mongo")
    public void saveNews(String message) throws JsonProcessingException {
        News news = newsConverter.objectFromString(message);
        NewsDocument document = mapper.map(news, NewsDocument.class);
        newsRepository.save(document);
    }

    @KafkaListener(topics = "users", groupId = "mongo")
    public void saveUser(String message) throws JsonProcessingException {
        User user = userConverter.objectFromString(message);
        UserDocument document = mapper.map(user, UserDocument.class);
        usersRepository.save(document);
    }
}
