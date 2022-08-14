package rs.miromaric.plutus.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("rs.miromaric.plutus.user")
@EnableTransactionManagement
@EnableR2dbcRepositories("rs.miromaric.plutus.user.repository")
public class SystemUserConfig {

}
