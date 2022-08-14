package rs.miromaric.plutus.paymentprovider.stream.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;
import rs.miromaric.plutus.paymentprovider.config.PaymentProviderProperties;
import rs.miromaric.plutus.paymentprovider.stream.serializer.DebitRequestDeserializer;
import rs.miromaric.plutus.paymentprovider.stream.serializer.DebitStatusDeserializer;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Component
public class DebitRequestReceiver {

    private final PaymentProviderProperties properties;
    private final SimpleDateFormat dateFormat;

    public DebitRequestReceiver(PaymentProviderProperties properties) {
        this.properties = properties;
        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z yyyy.MMM.dd ");
        consumeMessages();
    }

    public void consumeMessages() {
        ReceiverOptions<DebitStatus, DebitRequest> receiverOptions = getReceiverOptions();
        receiverOptions = receiverOptions
                .subscription(Collections.singleton(properties.getKafka().getDebitRequest().getTopic()))
                .addAssignListener(partitions -> log.debug("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> log.debug("onPartitionsRevoked {}", partitions));
        Flux<ReceiverRecord<DebitStatus, DebitRequest>> kafkaFlux = KafkaReceiver.create(receiverOptions).receive();
        kafkaFlux.subscribe(record -> {
            ReceiverOffset offset = record.receiverOffset();
            String recordLog = String.format("Received message: topic-partition=%s offset=%d timestamp=%s key=%s value=%s\n",
                    offset.topicPartition(),
                    offset.offset(),
                    dateFormat.format(new Date(record.timestamp())),
                    record.key(),
                    record.value());
            log.debug(recordLog);
            offset.acknowledge();
        });
    }

    private ReceiverOptions<DebitStatus, DebitRequest> getReceiverOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getKafka().getDebitRequest().getServers());
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, properties.getKafka().getDebitRequest().getClientId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getKafka().getDebitRequest().getAutoOffsetReset());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, DebitStatusDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DebitRequestDeserializer.class);
        return ReceiverOptions.create(props);
    }
}
