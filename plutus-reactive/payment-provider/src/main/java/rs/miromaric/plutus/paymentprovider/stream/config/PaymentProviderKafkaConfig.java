package rs.miromaric.plutus.paymentprovider.stream.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;
import rs.miromaric.plutus.paymentprovider.config.PaymentProviderProperties;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;

import java.util.Collections;
import java.util.Map;

@Configuration
public class PaymentProviderKafkaConfig {

    @Bean
    @Qualifier("debitKafkaConsumerTemplate")
    public ReactiveKafkaConsumerTemplate<String, DebitRequest> debitKafkaConsumerTemplate(
            PaymentProviderProperties providerProperties,
            KafkaProperties kafkaProperties) {

        String topic = providerProperties.getKafka().getDebitRequest().getTopic();

        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        ReceiverOptions<String, DebitRequest> receiverOptions = ReceiverOptions.create(props);
        receiverOptions.subscription(Collections.singletonList(topic));
        return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
    }

    @Bean
    @Qualifier("debitKafkaProducerTemplate")
    public ReactiveKafkaProducerTemplate<String, DebitResponse> debitKafkaProducerTemplate(
            PaymentProviderProperties providerProperties,
            KafkaProperties properties) {

        Map<String, Object> props = properties.buildProducerProperties();
        SenderOptions<String, DebitResponse> senderOptions = SenderOptions.create(props);
        return new ReactiveKafkaProducerTemplate<>(senderOptions);
    }

}
