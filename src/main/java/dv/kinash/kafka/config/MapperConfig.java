package dv.kinash.kafka.config;

import dv.kinash.kafka.consumers.mongo.document.NewsDocument;
import dv.kinash.kafka.consumers.mongo.document.UserDocument;
import dv.kinash.kafka.consumers.mssql.entity.NewsEntity;
import dv.kinash.kafka.consumers.mssql.entity.UserEntity;
import dv.kinash.kafka.model.News;
import dv.kinash.kafka.model.User;
import dv.kinash.kafka.producers.web.dto.NewsDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(new Converter<NewsDTO, News>() {
            @Override
            public News convert(MappingContext<NewsDTO, News> mappingContext) {
                NewsDTO newsDTO = mappingContext.getSource();
                News news = mappingContext.getDestination();
                if (news == null)
                    news = new News();
                news.setId(newsDTO.getId());
                news.setTitle(newsDTO.getTitle());
                news.setText(newsDTO.getText());
                return news;
            }
        });

        mapper.addConverter(new Converter<NewsDTO, User>() {
            @Override
            public User convert(MappingContext<NewsDTO, User> mappingContext) {
                NewsDTO newsDTO = mappingContext.getSource();
                User user = mappingContext.getDestination();
                if (user == null)
                    user = new User();
                user.setId(newsDTO.getUser_id());
                user.setName(newsDTO.getUser_name());
                return user;
            }
        });

        mapper.addConverter(new Converter<News, NewsDocument>() {
            @Override
            public NewsDocument convert(MappingContext<News, NewsDocument> mappingContext) {
                News news = mappingContext.getSource();
                NewsDocument document = mappingContext.getDestination();
                if (document == null)
                    document = new NewsDocument();
                document.setId(news.getId());
                document.setUserId(news.getUser().getId());
                document.setTitle(news.getTitle());
                document.setText(news.getText());
                document.setSource(news.getSource().toString());
                return document;
            }
        });

        mapper.addConverter(new Converter<User, UserDocument>() {
            @Override
            public UserDocument convert(MappingContext<User, UserDocument> mappingContext) {
                User user = mappingContext.getSource();
                UserDocument document = mappingContext.getDestination();
                if (document == null)
                    document = new UserDocument();
                document.setId(user.getId());
                document.setName(user.getName());
                document.setSource(user.getSource().toString());
                return document;
            }
        });

        mapper.addConverter(new Converter<News, NewsEntity>() {
            @Override
            public NewsEntity convert(MappingContext<News, NewsEntity> mappingContext) {
                News news = mappingContext.getSource();
                NewsEntity entity = mappingContext.getDestination();
                if (entity == null)
                    entity = new NewsEntity();
                entity.setId(news.getId());
                entity.setTitle(news.getTitle());
                entity.setText(news.getText());
                entity.setUser(getUserEntity(news.getUser(), null));
                entity.setSource(news.getSource().toString());
                return entity;
            }
        });

        mapper.addConverter(new Converter<User, UserEntity>() {
            @Override
            public UserEntity convert(MappingContext<User, UserEntity> mappingContext) {
                User user = mappingContext.getSource();
                UserEntity entity = mappingContext.getDestination();
                entity = getUserEntity(user, entity);
                return entity;
            }
        });

        return mapper;
    }

    private static UserEntity getUserEntity(User user, UserEntity entity) {
        if (entity == null)
            entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setSource(user.getSource().toString());
        return entity;
    }

}
