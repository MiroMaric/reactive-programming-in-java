package rs.miromaric.plutus.user.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import rs.miromaric.plutus.test.common.PlutusPostgreSQLTestContainer;
import rs.miromaric.plutus.user.repository.SystemUserRepository;
import rs.miromaric.plutus.user.repository.SystemUserRepositoryTest;

@SpringBootTest
@Testcontainers
public class TemplateSystemUserRepositoryTest extends SystemUserRepositoryTest {

    @Container
    @SuppressWarnings("unused")
    public static PostgreSQLContainer<?> postgreSQLContainer = PlutusPostgreSQLTestContainer.postgreSQLContainer;

    @Autowired
    private TemplateSystemUserRepository repository;

    @Override
    protected SystemUserRepository getRepository() {
        return repository;
    }
}
