package dv.kinash.kafka.consumers.mongo.repository;

import dv.kinash.kafka.consumers.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersDocRepository extends MongoRepository<UserDocument, Long> {
}