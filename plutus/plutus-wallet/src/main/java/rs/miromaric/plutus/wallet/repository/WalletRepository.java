package rs.miromaric.plutus.wallet.repository;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import rs.miromaric.plutus.common.exception.ResourceNotFoundException;
import rs.miromaric.plutus.common.repositry.PlutusJpaRepository;
import rs.miromaric.plutus.wallet.entity.WalletEntity;
import rs.miromaric.plutus.wallet.exception.WalletNotFoundException;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface WalletRepository extends PlutusJpaRepository<WalletEntity, String> {

    @Lock(LockModeType.READ)
    Optional<WalletEntity> findForReadByUuid(String uuid);

    @Lock(LockModeType.WRITE)
    Optional<WalletEntity> findForWriteByUuid(String uuid);

    default WalletEntity getForReadByUuid(String uuid) {
        return findForReadByUuid(uuid).orElseThrow(() -> getResourceNotFoundException(uuid));
    }

    default WalletEntity getForWriteByUuid(String uuid) {
        return findForWriteByUuid(uuid).orElseThrow(() -> getResourceNotFoundException(uuid));
    }

    @Override
    default ResourceNotFoundException getResourceNotFoundException(String id) {
        return WalletNotFoundException.withId(id);
    }
}
