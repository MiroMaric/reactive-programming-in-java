package rs.miromaric.plutus.payment.model.payout;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
@JsonDeserialize(as = ImmutablePayOutRequest.class)
@JsonSerialize(as = ImmutablePayOutRequest.class)
@Serial.Version(202107072355659003L)
public interface PayOutRequest {
    String getUserId();
    String getWalletId();
    String getCurrency();
    String getToken();
    BigDecimal getAmount();
}
