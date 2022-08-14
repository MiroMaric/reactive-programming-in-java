package rs.miromaric.plutus.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.common.model.HasUuid;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value.Immutable
@JsonDeserialize(as = ImmutableWallet.class)
@JsonSerialize(as = ImmutableWallet.class)
@Serial.Version(20210707001952693L)
public interface Wallet extends HasUuid {
    String getUserUuid();
    String getLabel();
    String getCurrency();
    @Value.Default
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    default BigDecimal getAmount() {
        return BigDecimal.ZERO.setScale(3, RoundingMode.UNNECESSARY);
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
