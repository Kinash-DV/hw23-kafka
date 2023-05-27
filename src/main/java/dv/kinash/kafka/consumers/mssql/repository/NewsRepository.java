package dv.kinash.kafka.consumers.mssql.repository;

import dv.kinash.kafka.consumers.mssql.entity.NewsEntity;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<NewsEntity, Long> {
}
