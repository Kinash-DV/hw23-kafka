package dv.kinash.kafka.producers.wiki;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dv.kinash.kafka.model.News;
import dv.kinash.kafka.model.Source;
import dv.kinash.kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class WikiClient {
    @Autowired
    private WebClient webClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Producer producer;

    @Scheduled(fixedRate = 60000)
    public void fetchDataFromAPI() {

        final String baseUri = "/api/rest_v1/page";

        String randomPageUrl = webClient.get()
            .uri(baseUri + "/random/title")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toBodilessEntity()
            .block()
            .getHeaders()
            .getLocation()
            .toString();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(randomPageUrl.substring(2));
        String encodedUrl = uriBuilder.build().encode().toUriString();

        String answer = webClient.get()
            .uri(baseUri + encodedUrl)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class)
            .onErrorResume(WebClientResponseException.class, ex -> Mono.just(""))
            .block();

        if (answer.isBlank()) return;

        try {
            Map<String, Object> resultMap = objectMapper.readValue(answer, Map.class);
            List<Map<String, Object>> items = (List<Map<String, Object>>) resultMap.get("items");
            for (Map<String, Object> item : items) {
                final Integer userId = (Integer) item.get("user_id");
                final String userText = (String) item.get("user_text");
                User user = new User(userId.longValue(), userText, Source.wiki);

                final Integer rev = (Integer) item.get("rev");
                final String title = (String) item.get("title");
                final String comment = (String) item.get("comment");
                News news = new News(rev.longValue(), title, comment, user, Source.wiki);

                producer.sendNews(news);
                producer.sendUser(user);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
