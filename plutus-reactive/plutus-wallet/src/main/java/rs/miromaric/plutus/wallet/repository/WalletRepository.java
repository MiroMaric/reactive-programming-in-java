package rs.miromaric.plutus.wallet.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.wallet.entity.WalletEntity;

@Repository
public interface WalletRepository extends ReactiveCrudRepository<WalletEntity, String> {

    @Query(value = "SELECT * FROM WALLET WHERE UUID = $1 FOR UPDATE")
    Mono<WalletEntity> findForReadByUuid(String uuid);

    @Query(value = "SELECT * FROM WALLET WHERE UUID = $1 FOR SHARE")
    Mono<WalletEntity> findForWriteByUuid(String uuid);

}
