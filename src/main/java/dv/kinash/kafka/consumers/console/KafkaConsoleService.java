package dv.kinash.kafka.consumers.console;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsoleService {

    @KafkaListener(topics = "news", groupId = "console")
    public void printNews(String message){
        System.out.println("News topic : " + message);
    }

    @KafkaListener(topics = "users", groupId = "console")
    public void printUser(String message){
        System.out.println("Users topic : " + message);
    }

}
