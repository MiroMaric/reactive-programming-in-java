package rs.miromaric.plutus.wallet.model.payout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.math.BigDecimal;

@Value.Immutable
@JsonDeserialize(as = ImmutableWPayOutRequest.class)
@JsonSerialize(as = ImmutableWPayOutRequest.class)
@Serial.Version(202107072355659022L)
public interface WPayOutRequest {
    String getWalletUuid();
    BigDecimal getAmount();

    static WPayOutRequest of(String uuid, BigDecimal amount) {
        return ImmutableWPayOutRequest
                .builder()
                .walletUuid(uuid)
                .amount(amount)
                .build();
    }

    @JsonIgnore
    default ImmutableWPayOutRequest copy() {
        return ImmutableWPayOutRequest.copyOf(this);
    }

}
