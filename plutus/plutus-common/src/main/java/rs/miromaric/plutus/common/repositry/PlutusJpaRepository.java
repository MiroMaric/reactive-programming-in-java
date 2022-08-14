package rs.miromaric.plutus.common.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import rs.miromaric.plutus.common.exception.ResourceNotFoundException;

@NoRepositoryBean
public interface PlutusJpaRepository<T, I> extends JpaRepository<T, I> {

    default T getByUuid(I id) {
        return findById(id).orElseThrow(() -> getResourceNotFoundException(id));
    }

    default ResourceNotFoundException getResourceNotFoundException(I id) {
        return new ResourceNotFoundException(id);
    }
}
