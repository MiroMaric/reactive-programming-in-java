package rs.miromaric.plutus.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class PlutusEntityId {
    @Id
    protected String uuid;
}
