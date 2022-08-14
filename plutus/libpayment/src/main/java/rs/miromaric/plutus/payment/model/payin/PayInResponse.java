package rs.miromaric.plutus.payment.model.payin;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value.Immutable
@JsonDeserialize(as = ImmutablePayInResponse.class)
@JsonSerialize(as = ImmutablePayInResponse.class)
@Serial.Version(202107072355650000L)
public interface PayInResponse {
    String getMerchantRefNum();
    Date getTime();
    PayInStatus getStatus();

    static PayInResponse of(String merchantRefNum, Date time, PayInStatus status) {
        return ImmutablePayInResponse
                .builder()
                .merchantRefNum(merchantRefNum)
                .time(time)
                .status(status)
                .build();
    }

    static PayInResponse of(String merchantRefNum, PayInStatus status) {
        return ImmutablePayInResponse
                .builder()
                .merchantRefNum(merchantRefNum)
                .time(new Date())
                .status(status)
                .build();
    }
}
