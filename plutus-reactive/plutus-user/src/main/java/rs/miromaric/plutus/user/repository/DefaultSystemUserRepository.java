package rs.miromaric.plutus.user.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import rs.miromaric.plutus.user.entity.SystemUserEntity;

@Repository
public interface DefaultSystemUserRepository extends ReactiveCrudRepository<SystemUserEntity, String> {

}
