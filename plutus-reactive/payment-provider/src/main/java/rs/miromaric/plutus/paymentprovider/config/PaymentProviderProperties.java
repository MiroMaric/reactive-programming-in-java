package rs.miromaric.plutus.paymentprovider.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "plutus.payment-provider")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProviderProperties {

    private String test;
    private Kafka kafka = new Kafka();

    @Data
    public static class Kafka {

        private Receiver debitRequest = new Receiver();
        private Sender debitResponse = new Sender();
        private Receiver creditRequest = new Receiver();
        private Sender creditResponse = new Sender();

        @Data
        public static class Sender {
            private List<String> servers;
            private String clientId;
            private String acksConfig = "all";
            private String topic = "debit-response-topic";
        }

        @Data
        public static class Receiver {
            private List<String> servers;
            private String clientId;
            private String autoOffsetReset = "earliest";
            private String topic = "debit-request-topic";
        }
    }

}
