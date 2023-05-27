package dv.kinash.kafka.consumers.mssql;

import com.fasterxml.jackson.core.JsonProcessingException;
import dv.kinash.kafka.consumers.mssql.entity.NewsEntity;
import dv.kinash.kafka.consumers.mssql.entity.UserEntity;
import dv.kinash.kafka.consumers.mssql.repository.NewsRepository;
import dv.kinash.kafka.consumers.mssql.repository.UsersRepository;
import dv.kinash.kafka.model.News;
import dv.kinash.kafka.model.User;
import dv.kinash.kafka.model.converter.NewsJsonConverter;
import dv.kinash.kafka.model.converter.UserJsonConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaSqlService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private NewsJsonConverter newsConverter;
    @Autowired
    private UserJsonConverter userConverter;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @KafkaListener(topics = "news", groupId = "mssql")
    public void saveNews(String message) throws JsonProcessingException {
        News news = newsConverter.objectFromString(message);
        NewsEntity entity = mapper.map(news, NewsEntity.class);

        // The message can be read before the user, who is not yet in the database.
        UserEntity user = entity.getUser();
        if (user != null)
            usersRepository.save(user);

        newsRepository.save(entity);
    }

    @KafkaListener(topics = "users", groupId = "mssql")
    public void saveUser(String message) throws JsonProcessingException {
        User user = userConverter.objectFromString(message);
        UserEntity entity = mapper.map(user, UserEntity.class);
    }
}
