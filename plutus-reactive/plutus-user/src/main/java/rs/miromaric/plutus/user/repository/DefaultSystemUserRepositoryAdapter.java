package rs.miromaric.plutus.user.repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.entity.SystemUserEntity;

@RequiredArgsConstructor
public class DefaultSystemUserRepositoryAdapter implements SystemUserRepository {

    private final DefaultSystemUserRepository repository;

    @Override
    public Mono<SystemUserEntity> save(SystemUserEntity user) {
        return repository.save(user);
    }

    @Override
    public Mono<SystemUserEntity> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<SystemUserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<SystemUserEntity> update(SystemUserEntity user) {
        return repository.save(user);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
