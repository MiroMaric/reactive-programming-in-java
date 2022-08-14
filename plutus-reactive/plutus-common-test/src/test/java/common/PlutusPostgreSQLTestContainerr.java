package common;

import org.testcontainers.containers.PostgreSQLContainer;

public class PlutusPostgreSQLTestContainerr {
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
            .withDatabaseName("plutus")
            .withUsername("plutus")
            .withPassword("plutus");
}
