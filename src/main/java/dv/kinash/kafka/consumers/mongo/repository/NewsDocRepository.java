package dv.kinash.kafka.consumers.mongo.repository;

import dv.kinash.kafka.consumers.mongo.document.NewsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsDocRepository extends MongoRepository<NewsDocument, Long> {
}