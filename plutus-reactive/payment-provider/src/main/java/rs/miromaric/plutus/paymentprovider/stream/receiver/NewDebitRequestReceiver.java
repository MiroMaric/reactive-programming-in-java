package rs.miromaric.plutus.paymentprovider.stream.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import rs.miromaric.plutus.paymentprovider.config.PaymentProviderProperties;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;

@Slf4j
@Component
public class NewDebitRequestReceiver {

    private final ReactiveKafkaConsumerTemplate<String, DebitRequest> kafkaConsumerTemplate;
    private final PaymentProviderProperties properties;

    public NewDebitRequestReceiver(@Qualifier("debitKafkaConsumerTemplate")
                                   ReactiveKafkaConsumerTemplate<String, DebitRequest> kafkaConsumerTemplate,
                                   PaymentProviderProperties properties) {
        this.kafkaConsumerTemplate = kafkaConsumerTemplate;
        this.properties = properties;
    }

    public Flux<DebitRequest> consumeDebitRequests() {
        return kafkaConsumerTemplate
                .receiveAutoAck()
                // .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
                .map(ConsumerRecord::value)
                .doOnNext(debitRequest -> log.info(debitRequest.toString()))
                .doOnError(throwable -> log.error("error: ", throwable.getMessage()));
    }

    @EventListener
    public void onContextRefresh(ContextRefreshedEvent event) {
        this.consumeDebitRequests().subscribe();
    }
}
