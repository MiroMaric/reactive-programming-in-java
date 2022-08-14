package rs.miromaric.plutus.user.repository;

import org.springframework.stereotype.Repository;
import rs.miromaric.plutus.common.exception.ResourceNotFoundException;
import rs.miromaric.plutus.common.repositry.PlutusJpaRepository;
import rs.miromaric.plutus.user.entity.SystemUserEntity;
import rs.miromaric.plutus.user.exception.SystemUserNotFoundException;

@Repository
public interface SystemUserRepository extends PlutusJpaRepository<SystemUserEntity, String> {

    @Override
    default ResourceNotFoundException getResourceNotFoundException(String id) {
        return SystemUserNotFoundException.withId(id);
    }

}
