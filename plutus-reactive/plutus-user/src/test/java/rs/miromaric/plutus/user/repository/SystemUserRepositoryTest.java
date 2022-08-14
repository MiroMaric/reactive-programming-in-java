package rs.miromaric.plutus.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import rs.miromaric.plutus.user.data.SystemUserDatabaseInitializer;
import rs.miromaric.plutus.user.entity.SystemUserEntity;

import java.util.UUID;

@Slf4j
public abstract class SystemUserRepositoryTest {

    protected abstract SystemUserRepository getRepository();

    @Autowired
    protected SystemUserDatabaseInitializer systemUserDatabaseInitializer;

    SystemUserEntity user;
    SystemUserRepository repository;

    @BeforeEach
    public void beforeEach() {
        user = new SystemUserEntity(
                UUID.randomUUID().toString(),
                "miko",
                "Miro",
                "Maric",
                true);

        repository = getRepository();

        StepVerifier.create(systemUserDatabaseInitializer.resetTables()).verifyComplete();
    }

    @AfterEach
    public void afterEach() {
        StepVerifier.create(systemUserDatabaseInitializer.clearTables()).verifyComplete();
    }

    @Test
    public void save() {
        Mono<SystemUserEntity> save = repository.save(user);

        StepVerifier.create(save)
                .expectNextMatches(savedUser -> assertUser(user, savedUser))
                .verifyComplete();
    }

    @Test
    public void findById() {
        Mono<SystemUserEntity> findById = repository.save(user)
                .flatMap(user -> repository.findById(user.getUuid()));

        StepVerifier.create(findById)
                .expectNextMatches(savedUser -> assertUser(user, savedUser))
                .verifyComplete();
    }

    @Test
    public void getAll() {
        Flux<SystemUserEntity> findAll = repository.save(user)
                .flatMapMany(user -> repository.findAll())
                .log();

        StepVerifier.create(findAll)
                .expectNextMatches(savedUser -> assertUser(user, savedUser))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void update() {
        SystemUserEntity user1 = new SystemUserEntity(user);
        user1.setFirstname("pera");

        Flux<SystemUserEntity> findAll = repository.save(user)
                .flatMapMany(user -> repository.update(user1))
                .log();

        StepVerifier.create(findAll)
                .expectNextMatches(savedUser -> assertUser(user1, savedUser))
                .expectNextCount(0)
                .verifyComplete();
    }

    protected boolean assertUser(SystemUserEntity expected, SystemUserEntity actual) {
        return expected.equals(actual);
    }

}
