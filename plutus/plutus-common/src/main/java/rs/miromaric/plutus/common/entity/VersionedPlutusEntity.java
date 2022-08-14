package rs.miromaric.plutus.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class VersionedPlutusEntity extends PlutusEntityId {
    @Version
    @Column(name = "VERSION", columnDefinition = "BIGINT DEFAULT 0")
    protected Long version = 0L;
}
