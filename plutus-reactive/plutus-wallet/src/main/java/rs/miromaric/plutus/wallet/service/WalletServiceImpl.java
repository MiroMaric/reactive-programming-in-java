package rs.miromaric.plutus.wallet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.wallet.entity.WalletEntity;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;
import rs.miromaric.plutus.wallet.repository.WalletRepository;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    //@todo: proveriti korisnika
    public Mono<Wallet> save(Wallet wallet) {
        WalletEntity entity = new WalletEntity(wallet);
        return walletRepository.save(entity).map(WalletEntity::getDefaultDto);
    }

    @Override
    public Mono<Wallet> get(String uuid) {
        return walletRepository.findForReadByUuid(uuid).map(WalletEntity::getDefaultDto);
    }

    @Override
    //@todo: proveriti korisnika
    public Mono<Wallet> update(Wallet wallet) {
        return walletRepository.findForReadByUuid(wallet.getUuid())
                .map(walletEntity -> walletEntity.update(wallet))
                .flatMap(walletRepository::save)
                .map(WalletEntity::getDefaultDto);
    }

    @Override
    public Mono<Wallet> delete(String uuid) {
        return walletRepository.findForReadByUuid(uuid)
                .flatMap(walletEntity -> walletRepository.deleteById(walletEntity.getUuid()).thenReturn(walletEntity))
                .map(WalletEntity::getDefaultDto);
    }

    @Override
    public Mono<WPayInResponse> payIn(WPayInRequest request) {
        return walletRepository.findForWriteByUuid(request.getWalletUuid())
                .flatMap(walletEntity -> {
                    if (request.getAmount().signum() != 1) {
                        return Mono.just(WPayInResponse.of(walletEntity.getDefaultDto(), WPayInStatus.INVALID_AMOUNT));
                    }
                    walletEntity.setAmount(walletEntity.getAmount().add(request.getAmount()));
                    return walletRepository.save(walletEntity)
                            .map(WalletEntity::getDefaultDto)
                            .map(defaultDto -> WPayInResponse.of(defaultDto, WPayInStatus.COMPLETED));
                });
    }

    @Override
    public Mono<WPayOutResponse> payOut(WPayOutRequest request) {
        return walletRepository.findForWriteByUuid(request.getWalletUuid())
                .flatMap(walletEntity -> {
                    if (request.getAmount().signum() != 1) {
                        return Mono.just(WPayOutResponse.of(walletEntity.getDefaultDto(), WPayOutStatus.INVALID_AMOUNT));
                    }
                    BigDecimal newAmount = walletEntity.getAmount().subtract(request.getAmount());
                    if (newAmount.signum() == -1) {
                        return Mono.just(WPayOutResponse.of(walletEntity.getDefaultDto(), WPayOutStatus.NOT_ENOUGH_FUNDS));
                    }
                    walletEntity.setAmount(newAmount);
                    return walletRepository.save(walletEntity).map(WalletEntity::getDefaultDto)
                            .map(defaultDto -> WPayOutResponse.of(defaultDto, WPayOutStatus.COMPLETED));
                });
    }
}
