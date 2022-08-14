package rs.miromaric.plutus.wallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.common.model.HasUuid;

import java.math.BigDecimal;

@Value.Immutable
@JsonDeserialize(as = ImmutableWallet.class)
@JsonSerialize(as = ImmutableWallet.class)
@Serial.Version(20210707001952693L)
public interface Wallet extends HasUuid {
    String getUserUuid();
    String getLabel();
    String getCurrency();
    @Value.Default
    default BigDecimal getAmount() {
        return BigDecimal.ZERO;
    }
    @Value.Default
    default Boolean isActive() {
        return true;
    }

    @JsonIgnore
    default ImmutableWallet copy() {
        return ImmutableWallet.copyOf(this);
    }
}
