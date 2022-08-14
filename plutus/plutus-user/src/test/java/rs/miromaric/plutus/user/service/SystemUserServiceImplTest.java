package rs.miromaric.plutus.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import rs.miromaric.plutus.user.common.config.PlutusPostgreSQLTestContainer;
import rs.miromaric.plutus.user.config.UserConfig;
import rs.miromaric.plutus.user.exception.SystemUserNotFoundException;
import rs.miromaric.plutus.user.model.ImmutableSystemUser;
import rs.miromaric.plutus.user.model.SystemUser;

import static org.junit.jupiter.api.Assertions.*;
import static rs.miromaric.plutus.user.data.UserTestData.MIRO;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {UserConfig.class})
class SystemUserServiceImplTest {

    @Container
    @SuppressWarnings("unused")
    public static PostgreSQLContainer<?> postgreSQLContainer = PlutusPostgreSQLTestContainer.postgreSQLContainer;

    @Autowired
    SystemUserService systemUserService;

    @Test
    void testAdd() {
        SystemUser savedUser = systemUserService.save(MIRO);
        assertNotNull(savedUser);
        assertResourceFields(MIRO, savedUser);
    }

    @Test
    void testGet() {
        systemUserService.save(MIRO);
        SystemUser savedUser = systemUserService.get(MIRO.getUuid());
        assertResourceFields(MIRO, savedUser);
    }

    @Test
    void testUpdate() {
        systemUserService.save(MIRO);
        SystemUser changedUser = ImmutableSystemUser.copyOf(MIRO).withUsername("mirom");
        SystemUser updatedUser = systemUserService.update(changedUser);
        assertResourceFields(changedUser, updatedUser);
    }

    @Test
    void testDelete() {
        systemUserService.save(MIRO);
        SystemUser deletedUser = systemUserService.delete(MIRO.getUuid());
        assertResourceFields(MIRO, deletedUser);
        assertThrows(SystemUserNotFoundException.class, () -> systemUserService.get(MIRO.getUuid()));
    }

    private void assertResourceFields(SystemUser expected, SystemUser actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstname(), actual.getFirstname());
        assertEquals(expected.getLastname(), actual.getLastname());
    }
}
