package rs.miromaric.plutus.paymentprovider.stream.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;

import java.nio.charset.StandardCharsets;

public class DebitResponseSerializer implements Serializer<DebitResponse> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public byte[] serialize(String topic, DebitResponse data) {
        return objectMapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8);
    }
}
