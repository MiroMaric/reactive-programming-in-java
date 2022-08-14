package rs.miromaric.plutus.provider.model.debit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.provider.model.PaymentRequest;

@Value.Immutable
@JsonDeserialize(as = ImmutableDebitRequest.class)
@JsonSerialize(as = ImmutableDebitRequest.class)
@Serial.Version(202107072355659852L)
public interface DebitRequest extends PaymentRequest {
}
