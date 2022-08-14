package rs.miromaric.plutus.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

@Data
public abstract class PlutusEntityId implements Persistable<String> {
    @Id
    protected String uuid;
}
