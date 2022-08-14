package rs.miromaric.plutus.common.entity;

public interface PlutusEntity<D> {
    PlutusEntity<D> update(D defaultDto);
    D getDefaultDto();
}
