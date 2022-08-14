package rs.miromaric.plutus.user.repository;

import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.entity.SystemUserEntity;

@NoRepositoryBean
public interface SystemUserRepository {
    Mono<SystemUserEntity> save(SystemUserEntity user);
    Mono<SystemUserEntity> findById(String id);
    Flux<SystemUserEntity> findAll();
    Mono<SystemUserEntity> update(SystemUserEntity user);
    Mono<Void> deleteById(String id);
}
