package rs.miromaric.plutus.paymentprovider.stream.serializer;

import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;

import java.nio.charset.StandardCharsets;

public class DebitStatusSerializer implements Serializer<DebitStatus> {

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, DebitStatus data) {
        return data.toString().getBytes(StandardCharsets.UTF_8);
    }
}
