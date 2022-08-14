package rs.miromaric.plutus.paymentprovider.stream.serializer;

import org.apache.kafka.common.serialization.Deserializer;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;

import java.nio.charset.StandardCharsets;

public class DebitStatusDeserializer implements Deserializer<DebitStatus> {

    @Override
    public DebitStatus deserialize(String topic, byte[] data) {
        return DebitStatus.valueOf(new String(data, StandardCharsets.UTF_8));
    }
}
