package rs.miromaric.plutus.provider.model.credit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.provider.model.PaymentResponse;

@Value.Immutable
@JsonDeserialize(as = ImmutableCreditResponse.class)
@JsonSerialize(as = ImmutableCreditResponse.class)
@Serial.Version(202107072355659057L)
public interface CreditResponse extends PaymentResponse {
    CreditStatus getStatus();
}
