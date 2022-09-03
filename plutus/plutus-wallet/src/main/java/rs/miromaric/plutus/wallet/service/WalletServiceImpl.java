package rs.miromaric.plutus.wallet.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.wallet.entity.WalletEntity;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;
import rs.miromaric.plutus.wallet.repository.WalletRepository;
import rs.miromaric.plutus.wallet.service.proxy.SystemUserService;

import java.math.BigDecimal;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final SystemUserService systemUserService;

    @Override
    public Wallet save(Wallet wallet) {
        validateUser(wallet.getUuid());
        WalletEntity entity = new WalletEntity(wallet);
        return walletRepository.save(entity).getDefaultDto();
    }

    @Override
    public Wallet get(String uuid) {
        return walletRepository.getForReadByUuid(uuid).getDefaultDto();
    }

    @Override
    public Wallet update(Wallet wallet) {
        validateUser(wallet.getUuid());
        WalletEntity entity = walletRepository.getForReadByUuid(wallet.getUuid());
        entity = entity.update(wallet);
        return walletRepository.save(entity).getDefaultDto();
    }

    @Override
    public Wallet delete(String uuid) {
        Wallet wallet = walletRepository.getForReadByUuid(uuid).getDefaultDto();
        walletRepository.deleteById(uuid);
        return wallet;
    }

    @Override
    public WPayInResponse payIn(WPayInRequest request) {
        WalletEntity walletEntity = walletRepository.getForWriteByUuid(request.getWalletUuid());
        if(request.getAmount().signum() != 1) {
            return WPayInResponse.of(walletEntity.getDefaultDto(), WPayInStatus.INVALID_AMOUNT);
        }
        walletEntity.setAmount(walletEntity.getAmount().add(request.getAmount()));
        Wallet defaultDto = walletRepository.save(walletEntity).getDefaultDto();
        return WPayInResponse.of(defaultDto, WPayInStatus.COMPLETED);
    }

    @Override
    public WPayOutResponse payOut(WPayOutRequest request) {
        WalletEntity walletEntity = walletRepository.getForWriteByUuid(request.getWalletUuid());
        if(request.getAmount().signum() != 1) {
            return WPayOutResponse.of(walletEntity.getDefaultDto(), WPayOutStatus.INVALID_AMOUNT);
        }
        BigDecimal newAmount = walletEntity.getAmount().subtract(request.getAmount());
        if(newAmount.signum() == -1) {
            return WPayOutResponse.of(walletEntity.getDefaultDto(), WPayOutStatus.NOT_ENOUGH_FUNDS);
        }
        walletEntity.setAmount(newAmount);
        Wallet defaultDto = walletRepository.save(walletEntity).getDefaultDto();
        return WPayOutResponse.of(defaultDto, WPayOutStatus.COMPLETED);
    }


    private void validateUser(String uuid) {
        SystemUser systemUser = systemUserService.get(uuid);
        if (!systemUser.isActive()) {
            throw new IllegalArgumentException("User must be active!");
        }
    }
}
