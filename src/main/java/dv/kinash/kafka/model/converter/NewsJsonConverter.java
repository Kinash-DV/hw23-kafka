package dv.kinash.kafka.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dv.kinash.kafka.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsJsonConverter {
    @Autowired
    private ObjectMapper mapper;

    public String objectToString(News obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
    public News objectFromString(String str) throws JsonProcessingException {
        return mapper.readValue(str, News.class);
    }
}
