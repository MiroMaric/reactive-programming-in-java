package rs.miromaric.plutus.user.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.serial.Serial;
import org.immutables.value.Value;
import rs.miromaric.plutus.common.model.HasUuid;

@Value.Immutable
@JsonSerialize(as = ImmutableSystemUser.class)
@JsonDeserialize(as = ImmutableSystemUser.class)
@Serial.Version(20210704175300256L)
public interface SystemUser extends HasUuid {
    String getUsername();
    String getFirstname();
    String getLastname();
    @Value.Default
    default Boolean isActive() {
        return true;
    }

    default ImmutableSystemUser copy() {
        return ImmutableSystemUser.copyOf(this);
    }
}
