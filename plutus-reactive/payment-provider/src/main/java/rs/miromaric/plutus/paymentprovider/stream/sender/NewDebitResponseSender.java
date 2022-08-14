package rs.miromaric.plutus.paymentprovider.stream.sender;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;
import rs.miromaric.plutus.paymentprovider.config.PaymentProviderProperties;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class NewDebitResponseSender {

    private final ReactiveKafkaProducerTemplate<String, DebitResponse> kafkaProducerTemplate;
    private final PaymentProviderProperties providerProperties;
    private final String topic;

    public NewDebitResponseSender(@Qualifier("debitKafkaProducerTemplate")
                                  ReactiveKafkaProducerTemplate<String, DebitResponse> kafkaProducerTemplate,
                                  PaymentProviderProperties providerProperties) {

        this.kafkaProducerTemplate = kafkaProducerTemplate;
        this.providerProperties = providerProperties;
        this.topic = providerProperties.getKafka().getDebitResponse().getTopic();
    }


    public Mono<DebitResponse> sendMessage(DebitResponse debitResponse) {
        //var producerRecord = new ProducerRecord<>(topic, debitResponse.getProviderRefNum(), debitResponse);
        //var message = Mono.just(SenderRecord.create(producerRecord, debitResponse));
        return kafkaProducerTemplate.send(topic, debitResponse)
                .doOnNext(result -> {
                    RecordMetadata metadata = result.recordMetadata();
                    String resultLog = String.format("Message %s sent successfully, topic-partition=%s-%d offset=%d timestamp=%s\n",
                            result.correlationMetadata(),
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            new SimpleDateFormat("HH:mm:ss:SSS z yyyy.MMM.dd ").format(new Date(metadata.timestamp())));
                    log.debug(resultLog);
                })
                .doOnError(e -> log.error("Error:", e))
                .map(unused -> debitResponse);
    }
}
