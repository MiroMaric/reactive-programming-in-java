package rs.miromaric.plutus.wallet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("rs.miromaric.plutus.wallet")
@EnableTransactionManagement
@EnableR2dbcRepositories("rs.miromaric.plutus.wallet.repository")
public class WalletConfig {

}
