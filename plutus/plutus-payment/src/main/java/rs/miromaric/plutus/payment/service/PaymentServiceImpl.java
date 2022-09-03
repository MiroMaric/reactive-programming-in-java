package rs.miromaric.plutus.payment.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rs.miromaric.plutus.payment.model.Transfer;
import rs.miromaric.plutus.payment.model.payin.PayInRequest;
import rs.miromaric.plutus.payment.model.payin.PayInResponse;
import rs.miromaric.plutus.payment.model.payin.PayInStatus;
import rs.miromaric.plutus.payment.model.payout.PayOutRequest;
import rs.miromaric.plutus.payment.model.payout.PayOutResponse;
import rs.miromaric.plutus.payment.model.payout.PayOutStatus;
import rs.miromaric.plutus.payment.service.proxy.PaymentProviderService;
import rs.miromaric.plutus.payment.service.proxy.SystemUserService;
import rs.miromaric.plutus.payment.service.proxy.WalletService;
import rs.miromaric.plutus.provider.model.credit.CreditRequest;
import rs.miromaric.plutus.provider.model.credit.CreditResponse;
import rs.miromaric.plutus.provider.model.credit.CreditStatus;
import rs.miromaric.plutus.provider.model.credit.ImmutableCreditRequest;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;
import rs.miromaric.plutus.provider.model.debit.ImmutableDebitRequest;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final SystemUserService systemUserService;
    private final WalletService walletService;
    private final PaymentProviderService paymentProviderService;
    private final TransferService transferService;

    @Override
    public PayInResponse payIn(PayInRequest payInRequest) {
        String merchantRefUuid = UUID.randomUUID().toString();

        SystemUser systemUser = systemUserService.get(payInRequest.getUserId());
        log.debug(systemUser.toString());

        Wallet wallet = walletService.get(payInRequest.getWalletId());
        log.debug(wallet.toString());

        Optional<PayInStatus> payInStatus = checkPayInValidityOfUserAndHisWallet(systemUser, wallet);
        if(payInStatus.isPresent()) {
            return PayInResponse.of(merchantRefUuid, payInStatus.get());
        }

        DebitRequest debitRequest = ImmutableDebitRequest
                .builder()
                .accountId(payInRequest.PLUTUS_ACCOUNT_ID)
                .merchantRefNum(merchantRefUuid)
                .amount(payInRequest.getAmount())
                .token(payInRequest.getToken())
                .build();

        DebitResponse debitResponse = paymentProviderService.debit(debitRequest);
        log.debug(debitResponse.toString());

        if (debitResponse.getStatus().equals(DebitStatus.COMPLETED)) {
            WPayInResponse wPayInResponse = walletService.payIn(wallet.getUuid(), WPayInRequest.of(wallet.getUuid(), payInRequest.getAmount()));
            log.debug(wPayInResponse.toString());

            if (wPayInResponse.getStatus().equals(WPayInStatus.COMPLETED)) {
                transferService.save(createTransfer(payInRequest, merchantRefUuid));
                return PayInResponse.of(merchantRefUuid, PayInStatus.COMPLETED);
            }

        }
        return PayInResponse.of(merchantRefUuid, PayInStatus.FAILED);
    }

    @Override
    public PayOutResponse payOut(PayOutRequest payOutRequest) {
        String merchantRefUuid = UUID.randomUUID().toString();

        SystemUser systemUser = systemUserService.get(payOutRequest.getUserId());
        log.debug(systemUser.toString());

        Wallet wallet = walletService.get(payOutRequest.getWalletId());
        log.debug(wallet.toString());

        Optional<PayOutStatus> payOutStatus = checkPayOutValidityOfUserAndHisWallet(systemUser, wallet);
        if(payOutStatus.isPresent()) {
            return PayOutResponse.of(merchantRefUuid, payOutStatus.get());
        }

        CreditRequest creditRequest = ImmutableCreditRequest
                .builder()
                .accountId(payOutRequest.PLUTUS_ACCOUNT_ID)
                .merchantRefNum(merchantRefUuid)
                .amount(payOutRequest.getAmount())
                .token(payOutRequest.getToken())
                .build();

        CreditResponse creditResponse = paymentProviderService.credit(creditRequest);
        log.debug(creditResponse.toString());

        if (creditResponse.getStatus().equals(CreditStatus.COMPLETED)) {
            WPayOutResponse wwPayOutResponse = walletService.payOut(wallet.getUuid(), WPayOutRequest.of(wallet.getUuid(), payOutRequest.getAmount()));
            log.debug(wwPayOutResponse.toString());

            if (wwPayOutResponse.getStatus().equals(WPayOutStatus.COMPLETED)) {
                transferService.save(createTransfer(payOutRequest, merchantRefUuid));
                return PayOutResponse.of(merchantRefUuid, PayOutStatus.COMPLETED);
            }

        }
        return PayOutResponse.of(merchantRefUuid, PayOutStatus.FAILED);
    }

    private Transfer createTransfer(PayInRequest payInRequest, String merchantRefUuid) {
        return new Transfer(
                UUID.randomUUID().toString(),
                merchantRefUuid,
                payInRequest.getWalletId(),
                payInRequest.getAmount());
    }

    private Transfer createTransfer(PayOutRequest payOutRequest, String merchantRefUuid) {
        return new Transfer(
                UUID.randomUUID().toString(),
                merchantRefUuid,
                payOutRequest.getWalletId(),
                payOutRequest.getAmount().negate());
    }

    private Optional<PayInStatus> checkPayInValidityOfUserAndHisWallet(SystemUser systemUser, Wallet wallet) {
        if (!systemUser.isActive()) {
            return Optional.of(PayInStatus.USER_INACTIVE);
        }
        if (!systemUser.getUuid().equals(wallet.getUserUuid())) {
            return Optional.of(PayInStatus.WALLET_WRONG);
        }
        if (!wallet.isActive()) {
            return Optional.of(PayInStatus.WALLET_INACTIVE);
        }
        return Optional.empty();
    }

    private Optional<PayOutStatus> checkPayOutValidityOfUserAndHisWallet(SystemUser systemUser, Wallet wallet) {
        if (!systemUser.isActive()) {
            return Optional.of(PayOutStatus.INACTIVE_USER);
        }
        if (!systemUser.getUuid().equals(wallet.getUserUuid())) {
            return Optional.of(PayOutStatus.WRONG_WALLET);
        }
        if (!wallet.isActive()) {
            return Optional.of(PayOutStatus.INACTIVE_WALLET);
        }
        return Optional.empty();
    }
}
