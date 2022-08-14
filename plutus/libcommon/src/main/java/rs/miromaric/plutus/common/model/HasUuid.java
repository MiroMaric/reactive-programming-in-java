package rs.miromaric.plutus.common.model;

import org.immutables.value.Value;

import java.util.UUID;

public interface HasUuid {
    @Value.Default
    default String getUuid() {
        return UUID.randomUUID().toString();
    }
}
