package rs.miromaric.plutus.wallet.model.payin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
@JsonDeserialize(as = ImmutableWPayInRequest.class)
@JsonSerialize(as = ImmutableWPayInRequest.class)
@Serial.Version(202107072355659029L)
public interface WPayInRequest {
    String getWalletUuid();
    BigDecimal getAmount();

    static WPayInRequest of(String uuid, BigDecimal amount) {
        return ImmutableWPayInRequest
                .builder()
                .walletUuid(uuid)
                .amount(amount)
                .build();
    }

    @JsonIgnore
    default ImmutableWPayInRequest copy() {
        return ImmutableWPayInRequest.copyOf(this);
    }
}
