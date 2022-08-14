package rs.miromaric.plutus.paymentprovider.stream.sender;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;
import rs.miromaric.plutus.paymentprovider.config.PaymentProviderProperties;
import rs.miromaric.plutus.paymentprovider.stream.serializer.DebitResponseSerializer;
import rs.miromaric.plutus.paymentprovider.stream.serializer.DebitStatusSerializer;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Component
public class DebitResponseSender {

    private final PaymentProviderProperties properties;
    private final KafkaSender<DebitStatus, DebitResponse> sender;
    private final String topic;
    private final SimpleDateFormat dateFormat;

    public DebitResponseSender(PaymentProviderProperties properties) {
        this.properties = properties;
        sender = KafkaSender.create(getDebitResponseSenderOptions(properties));
        topic = properties.getKafka().getDebitResponse().getTopic();
        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z yyyy.MMM.dd ");
    }

    public Mono<DebitResponse> sendMessage(DebitResponse debitResponse) {
        var producerRecord = new ProducerRecord<>(topic, debitResponse.getStatus(), debitResponse);
        var message = Mono.just(SenderRecord.create(producerRecord, debitResponse));
        return sender.send(message)
                .doOnError(e -> log.error("Send failed", e))
                .doOnNext(result -> {
                    RecordMetadata metadata = result.recordMetadata();
                    String resultLog = String.format("Message %s sent successfully, topic-partition=%s-%d offset=%d timestamp=%s\n",
                            result.correlationMetadata(),
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            dateFormat.format(new Date(metadata.timestamp())));
                    log.debug(resultLog);
                }).map(SenderResult::correlationMetadata)
                .last();
    }


    private SenderOptions<DebitStatus, DebitResponse> getDebitResponseSenderOptions(PaymentProviderProperties properties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getKafka().getDebitResponse().getServers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getKafka().getDebitResponse().getClientId());
        props.put(ProducerConfig.ACKS_CONFIG, properties.getKafka().getDebitResponse().getAcksConfig());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, DebitStatusSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DebitResponseSerializer.class);
        return SenderOptions.create(props);
    }
}
