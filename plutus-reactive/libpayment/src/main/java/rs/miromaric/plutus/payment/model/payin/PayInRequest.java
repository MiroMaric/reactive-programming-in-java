package rs.miromaric.plutus.payment.model.payin;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
@JsonDeserialize(as = ImmutablePayInRequest.class)
@JsonSerialize(as = ImmutablePayInRequest.class)
@Serial.Version(202107072355659502L)
public interface PayInRequest {
    String getUserId();
    String getWalletId();
    String getCurrency();
    String getToken();
    BigDecimal getAmount();
}
