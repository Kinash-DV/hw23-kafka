package dv.kinash.kafka.producers.web.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import dv.kinash.kafka.producers.web.dto.NewsDTO;
import dv.kinash.kafka.producers.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api")
public class RestController {
    @Autowired
    private NewsService service;

    @PostMapping("/news")
    @ResponseStatus(HttpStatus.OK)
    public void createNews(@RequestBody NewsDTO news) throws JsonProcessingException {
        service.sendNews(news);
    }

}
