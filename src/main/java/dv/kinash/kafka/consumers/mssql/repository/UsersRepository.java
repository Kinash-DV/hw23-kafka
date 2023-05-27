package dv.kinash.kafka.consumers.mssql.repository;

import dv.kinash.kafka.consumers.mssql.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}
