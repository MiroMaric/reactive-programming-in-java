package rs.miromaric.plutus.paymentprovider.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PaymentProviderProperties.class)
public class PaymentProviderConfig {
}
