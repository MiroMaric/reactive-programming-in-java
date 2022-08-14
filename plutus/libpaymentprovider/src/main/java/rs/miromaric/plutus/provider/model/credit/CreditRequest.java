package rs.miromaric.plutus.provider.model.credit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.provider.model.PaymentRequest;

@Value.Immutable
@JsonDeserialize(as = ImmutableCreditRequest.class)
@JsonSerialize(as = ImmutableCreditRequest.class)
@Serial.Version(202107072355659048L)
public interface CreditRequest extends PaymentRequest {

}
