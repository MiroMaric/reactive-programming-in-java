package rs.miromaric.plutus.payment.repository;

import org.springframework.stereotype.Repository;
import rs.miromaric.plutus.common.repositry.PlutusJpaRepository;
import rs.miromaric.plutus.payment.entity.TransferEntity;

@Repository
public interface TransferRepository extends PlutusJpaRepository<TransferEntity, String> {

}
