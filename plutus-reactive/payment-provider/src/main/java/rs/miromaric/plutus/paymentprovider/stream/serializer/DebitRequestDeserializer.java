package rs.miromaric.plutus.paymentprovider.stream.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;

public class DebitRequestDeserializer implements Deserializer<DebitRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public DebitRequest deserialize(String topic, byte[] data) {
        return objectMapper.readValue(data, DebitRequest.class);
    }
}
