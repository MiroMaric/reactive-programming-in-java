package rs.miromaric.plutus.user.repository.impl;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import rs.miromaric.plutus.test.common.PlutusPostgreSQLTestContainer;
import rs.miromaric.plutus.user.repository.SystemUserRepository;
import rs.miromaric.plutus.user.repository.SystemUserRepositoryTest;
import rs.miromaric.plutus.user.repository.impl.SimpleSystemUserRepository;

@SpringBootTest
@Disabled
@Testcontainers
public class SimpleSystemUserRepositoryTest extends SystemUserRepositoryTest {

    @Container
    @SuppressWarnings("unused")
    public static PostgreSQLContainer<?> postgreSQLContainer = PlutusPostgreSQLTestContainer.postgreSQLContainer;

    @Autowired
    SimpleSystemUserRepository simpleSystemUserRepository;

    @Override
    public SystemUserRepository getRepository() {
        return simpleSystemUserRepository;
    }
}
