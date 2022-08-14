package rs.miromaric.plutus.wallet.model.payout;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.wallet.model.Wallet;

@Value.Immutable
@JsonDeserialize(as = ImmutableWPayOutResponse.class)
@JsonSerialize(as = ImmutableWPayOutResponse.class)
@Serial.Version(202107072355659593L)
public interface WPayOutResponse {
    Wallet getWallet();
    WPayOutStatus getStatus();

    static WPayOutResponse of(Wallet wallet, WPayOutStatus status) {
        return ImmutableWPayOutResponse
                .builder()
                .wallet(wallet)
                .status(status)
                .build();
    }
}
