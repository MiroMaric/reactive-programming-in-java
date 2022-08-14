package rs.miromaric.plutus.provider.model.debit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.provider.model.PaymentResponse;

import java.util.Date;

@Value.Immutable
@JsonDeserialize(as = ImmutableDebitResponse.class)
@JsonSerialize(as = ImmutableDebitResponse.class)
@Serial.Version(202107072355659852L)
public interface DebitResponse extends PaymentResponse {
    DebitStatus getStatus();
}
