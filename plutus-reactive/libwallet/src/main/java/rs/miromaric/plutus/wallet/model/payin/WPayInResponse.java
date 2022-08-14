package rs.miromaric.plutus.wallet.model.payin;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.wallet.model.Wallet;

@Value.Immutable
@JsonDeserialize(as = ImmutableWPayInResponse.class)
@JsonSerialize(as = ImmutableWPayInResponse.class)
@Serial.Version(202107072355659008L)
public interface WPayInResponse {
    Wallet getWallet();
    WPayInStatus getStatus();

    static WPayInResponse of(Wallet wallet, WPayInStatus status) {
        return ImmutableWPayInResponse
                .builder()
                .wallet(wallet)
                .status(status)
                .build();
    }
}
