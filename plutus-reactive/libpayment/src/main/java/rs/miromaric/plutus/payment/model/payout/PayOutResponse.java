package rs.miromaric.plutus.payment.model.payout;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
@JsonDeserialize(as = ImmutablePayOutResponse.class)
@JsonSerialize(as = ImmutablePayOutResponse.class)
@Serial.Version(202107072355650000L)
public interface PayOutResponse {
    String getMerchantRefNum();
    Date getTime();
    PayOutStatus getStatus();

    static PayOutResponse of(String merchantRefNum, Date time, PayOutStatus status) {
        return ImmutablePayOutResponse
                .builder()
                .merchantRefNum(merchantRefNum)
                .time(time)
                .status(status)
                .build();
    }

    static PayOutResponse of(String merchantRefNum, PayOutStatus status) {
        return ImmutablePayOutResponse
                .builder()
                .merchantRefNum(merchantRefNum)
                .time(new Date())
                .status(status)
                .build();
    }
}
