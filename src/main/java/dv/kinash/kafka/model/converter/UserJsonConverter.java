package dv.kinash.kafka.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dv.kinash.kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserJsonConverter {
    @Autowired
    private ObjectMapper mapper;

    public String objectToString(User obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
    public User objectFromString(String str) throws JsonProcessingException {
        return mapper.readValue(str, User.class);
    }
}
