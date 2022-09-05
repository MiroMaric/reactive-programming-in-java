package rs.miromaric.plutus.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.payment.model.payin.PayInRequest;
import rs.miromaric.plutus.payment.model.payin.PayInResponse;
import rs.miromaric.plutus.payment.model.payin.PayInStatus;
import rs.miromaric.plutus.payment.model.payout.PayOutRequest;
import rs.miromaric.plutus.payment.model.payout.PayOutResponse;
import rs.miromaric.plutus.payment.model.payout.PayOutStatus;
import rs.miromaric.plutus.payment.service.proxy.PaymentProviderApi;
import rs.miromaric.plutus.payment.service.proxy.SystemUserApi;
import rs.miromaric.plutus.payment.service.proxy.WalletApi;
import rs.miromaric.plutus.provider.model.credit.CreditRequest;
import rs.miromaric.plutus.provider.model.credit.CreditStatus;
import rs.miromaric.plutus.provider.model.credit.ImmutableCreditRequest;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;
import rs.miromaric.plutus.provider.model.debit.ImmutableDebitRequest;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final SystemUserApi systemUserApi;
    private final WalletApi walletApi;
    private final PaymentProviderApi paymentProviderApi;

    @Override
    public Mono<PayInResponse> payIn(PayInRequest payInRequest) {
        final String merchantRefUuid = UUID.randomUUID().toString();

        Mono<PayInResponse> failedResponse = Mono.just(PayInResponse.of(merchantRefUuid, PayInStatus.FAILED));
        return Mono.zip(systemUserApi.get(payInRequest.getUserId()), walletApi.get(payInRequest.getWalletId()))
                .flatMap(data -> {
                    SystemUser systemUser = data.getT1();
                    Wallet wallet = data.getT2();
                    Optional<PayInStatus> payInStatus = checkPayInValidityOfUserAndHisWallet(systemUser, wallet);
                    if (payInStatus.isPresent()) {
                        return Mono.just(PayInResponse.of(merchantRefUuid, payInStatus.get()));
                    }

                    DebitRequest debitRequest = createDebitRequest(payInRequest, merchantRefUuid);
                    return paymentProviderApi.debit(debitRequest)
                            .filter(debitResponse -> debitResponse.getStatus().equals(DebitStatus.COMPLETED))
                            .flatMap(debitResponse ->
                                walletApi.payIn(wallet.getUuid(), WPayInRequest.of(wallet.getUuid(), payInRequest.getAmount()))
                                        .filter(wPayInResponse -> wPayInResponse.getStatus().equals(WPayInStatus.COMPLETED))
                                        .map(wPayInResponse -> PayInResponse.of(merchantRefUuid, PayInStatus.COMPLETED))
                                        .switchIfEmpty(failedResponse)
                            ).switchIfEmpty(failedResponse);
                }).onErrorResume(throwable -> failedResponse);
    }

    private ImmutableDebitRequest createDebitRequest(PayInRequest payInRequest, String merchantRefUuid) {
        return ImmutableDebitRequest
                .builder()
                .accountId(PayInRequest.PLUTUS_ACCOUNT_ID)
                .merchantRefNum(merchantRefUuid)
                .amount(payInRequest.getAmount())
                .token(payInRequest.getToken())
                .build();
    }

    @Override
    public Mono<PayOutResponse> payOut(PayOutRequest payOutRequest) {
        final String merchantRefUuid = UUID.randomUUID().toString();

        Mono<PayOutResponse> failedResponse = Mono.just(PayOutResponse.of(merchantRefUuid, PayOutStatus.FAILED));
        return Mono.zip(systemUserApi.get(payOutRequest.getUserId()), walletApi.get(payOutRequest.getWalletId()))
                .flatMap(data -> {
                    SystemUser systemUser = data.getT1();
                    Wallet wallet = data.getT2();
                    Optional<PayOutStatus> payOutStatus = checkPayOutValidityOfUserAndHisWallet(systemUser, wallet);
                    if (payOutStatus.isPresent()) {
                        return Mono.just(PayOutResponse.of(merchantRefUuid, payOutStatus.get()));
                    }

                    CreditRequest creditRequest = createCreditRequest(payOutRequest, merchantRefUuid);
                    return paymentProviderApi.credit(creditRequest)
                            .filter(creditResponse -> creditResponse.getStatus().equals(CreditStatus.COMPLETED))
                            .flatMap(creditResponse ->
                                walletApi.payOut(wallet.getUuid(), WPayOutRequest.of(wallet.getUuid(), payOutRequest.getAmount()))
                                        .filter(wPayOutResponse -> wPayOutResponse.getStatus().equals(WPayOutStatus.COMPLETED))
                                        .map(wPayOutResponse -> PayOutResponse.of(merchantRefUuid, PayOutStatus.COMPLETED))
                                        .switchIfEmpty(failedResponse)
                            ).switchIfEmpty(failedResponse);
                }).onErrorResume(throwable -> failedResponse);
    }

    private ImmutableCreditRequest createCreditRequest(PayOutRequest payOutRequest, String merchantRefUuid) {
        return ImmutableCreditRequest
                .builder()
                .accountId(payOutRequest.PLUTUS_ACCOUNT_ID)
                .merchantRefNum(merchantRefUuid)
                .amount(payOutRequest.getAmount())
                .token(payOutRequest.getToken())
                .build();
    }

    private Optional<PayInStatus> checkPayInValidityOfUserAndHisWallet(SystemUser systemUser, Wallet wallet) {
        //@todo: dodati isActive polje
        //if (!systemUser.isActive()) {
        //    return Optional.of(PayInStatus.INACTIVE_USER);
        //}
        if (!systemUser.getUuid().equals(wallet.getUserUuid())) {
            return Optional.of(PayInStatus.WRONG_WALLET);
        }
        if (!wallet.isActive()) {
            return Optional.of(PayInStatus.INACTIVE_WALLET);
        }
        return Optional.empty();
    }

    private Optional<PayOutStatus> checkPayOutValidityOfUserAndHisWallet(SystemUser systemUser, Wallet wallet) {
        //@todo: dodati isActive polje
        //if (!systemUser.isActive()) {
        //    return Optional.of(PayOutStatus.INACTIVE_USER);
        //}
        if (!systemUser.getUuid().equals(wallet.getUserUuid())) {
            return Optional.of(PayOutStatus.WRONG_WALLET);
        }
        if (!wallet.isActive()) {
            return Optional.of(PayOutStatus.INACTIVE_WALLET);
        }
        return Optional.empty();
    }
}
