package rs.miromaric.plutus.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import rs.miromaric.plutus.test.common.PlutusPostgreSQLTestContainer;
import rs.miromaric.plutus.user.data.SystemUserDatabaseInitializer;
import rs.miromaric.plutus.user.model.SystemUser;

import static rs.miromaric.plutus.user.data.SystemUserTestData.MIKO;

@SpringBootTest
@Testcontainers
class SystemUserServiceTest {

    @Container
    @SuppressWarnings("unused")
    public static PostgreSQLContainer<?> postgreSQLContainer =
            PlutusPostgreSQLTestContainer.postgreSQLContainer;

    @Autowired
    protected SystemUserDatabaseInitializer systemUserDatabaseInitializer;

    @Autowired
    SystemUserService systemUserService;

    @BeforeEach
    public void beforeEach() {
        StepVerifier.create(systemUserDatabaseInitializer.resetTables())
                .verifyComplete();
    }

    @AfterEach
    public void afterEach() {
        StepVerifier.create(systemUserDatabaseInitializer.clearTables())
                .verifyComplete();
    }

    @Test
    void testAdd() {
        StepVerifier.create(systemUserService.save(MIKO))
                .expectNextMatches(user -> assertResourceFields(MIKO, user))
                .verifyComplete();
    }

    @Test
    void testGet() {
        Mono<SystemUser> savedUser = systemUserService.save(MIKO)
                .flatMap(user -> systemUserService.get(user.getUuid()));

        StepVerifier.create(savedUser)
                .expectNextMatches(user -> assertResourceFields(MIKO, user))
                .verifyComplete();
    }

    @Test
    void testUpdate() {
        Mono<SystemUser> updatedUser = systemUserService.save(MIKO)
                .map(user -> user.toBuilder().username("mirom").build())
                .flatMap(user -> systemUserService.get(user.getUuid()));

        StepVerifier.create(updatedUser)
                .expectNextMatches(user -> assertResourceFields(MIKO, user))
                .verifyComplete();
    }

    @Test
    void testDelete() {
        Mono<SystemUser> deletedUser = systemUserService.save(MIKO)
                .flatMap(user -> systemUserService.delete(user.getUuid()));

        StepVerifier.create(deletedUser)
                .expectNextMatches(user -> assertResourceFields(MIKO, user))
                .verifyComplete();

        StepVerifier.create(systemUserService.delete(MIKO.getUuid()))
                .expectNextCount(0)
                .verifyComplete();
    }

    private boolean assertResourceFields(SystemUser expected, SystemUser actual) {
        return expected.equals(actual);
    }

}
