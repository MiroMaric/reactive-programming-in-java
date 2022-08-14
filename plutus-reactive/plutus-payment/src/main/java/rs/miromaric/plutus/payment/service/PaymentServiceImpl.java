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

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final SystemUserApi systemUserApi;
    private final WalletApi walletApi;
    private final PaymentProviderApi paymentProviderApi;

    @Override
    public Mono<PayInResponse> payIn(PayInRequest payInRequest) {
        final String merchantRefUuid = "merchantRefUuid";
        final String plutusAccountId = "plutusAccountId";

        return Mono.zip(systemUserApi.get(payInRequest.getUserId()), walletApi.get(payInRequest.getWalletId()))
                .flatMap(data -> {
                    log.debug(data.toString());
                    System.out.println(data);
                    SystemUser systemUser = data.getT1();
                    Wallet wallet = data.getT2();
                    Optional<PayInStatus> payInStatus = checkPayInValidityOfUserAndHisWallet(systemUser, wallet);
                    log.debug(payInStatus.toString());
                    System.out.println(payInStatus);
                    if (payInStatus.isPresent()) {
                        return Mono.just(PayInResponse.of(merchantRefUuid, payInStatus.get()));
                    }

                    DebitRequest debitRequest = ImmutableDebitRequest
                            .builder()
                            .accountId(plutusAccountId)
                            .merchantRefNum(merchantRefUuid)
                            .amount(payInRequest.getAmount())
                            .token(payInRequest.getToken())
                            .build();

                    return paymentProviderApi.debit(debitRequest)
                            .flatMap(debitResponse -> {
                                log.debug(debitResponse.toString());
                                System.out.println(debitResponse);
                                if (debitResponse.getStatus().equals(DebitStatus.COMPLETED)) {
                                    return walletApi.payIn(wallet.getUuid(), WPayInRequest.of(wallet.getUuid(), payInRequest.getAmount()))
                                            .map(wPayInResponse -> {
                                                log.debug(wPayInResponse.toString());
                                                System.out.println(wPayInResponse);
                                                if (wPayInResponse.getStatus().equals(WPayInStatus.COMPLETED)) {
                                                    return PayInResponse.of(merchantRefUuid, PayInStatus.COMPLETED);
                                                }
                                                return PayInResponse.of(merchantRefUuid, PayInStatus.FAILED);
                                            });
                                } else {
                                    return Mono.just(PayInResponse.of(merchantRefUuid, PayInStatus.FAILED));
                                }
                            });
                }).onErrorResume(throwable -> {
                    log.error(throwable.toString(), throwable);
                    return Mono.just(PayInResponse.of(merchantRefUuid, PayInStatus.FAILED));
                });
    }

    @Override
    public Mono<PayOutResponse> payOut(PayOutRequest payOutRequest) {
        final String merchantRefUuid = "merchantRefUuid";
        final String plutusAccountId = "plutusAccountId";

        return Mono.zip(systemUserApi.get(payOutRequest.getUserId()), walletApi.get(payOutRequest.getWalletId()))
                .flatMap(data -> {
                    log.debug(data.toString());
                    SystemUser systemUser = data.getT1();
                    Wallet wallet = data.getT2();
                    Optional<PayOutStatus> payOutStatus = checkPayOutValidityOfUserAndHisWallet(systemUser, wallet);
                    if (payOutStatus.isPresent()) {
                        return Mono.just(PayOutResponse.of(merchantRefUuid, payOutStatus.get()));
                    }

                    CreditRequest creditRequest = ImmutableCreditRequest
                            .builder()
                            .accountId(plutusAccountId)
                            .merchantRefNum(merchantRefUuid)
                            .amount(payOutRequest.getAmount())
                            .token(payOutRequest.getToken())
                            .build();

                    return paymentProviderApi.credit(creditRequest)
                            .flatMap(creditResponse -> {
                                log.debug(creditResponse.toString());

                                if (creditResponse.getStatus().equals(CreditStatus.COMPLETED)) {
                                    return walletApi.payOut(wallet.getUuid(), WPayOutRequest.of(wallet.getUuid(), payOutRequest.getAmount()))
                                            .map(wPayOutResponse -> {
                                                log.debug(wPayOutResponse.toString());
                                                if (wPayOutResponse.getStatus().equals(WPayOutStatus.COMPLETED)) {
                                                    return PayOutResponse.of(merchantRefUuid, PayOutStatus.COMPLETED);
                                                }
                                                return PayOutResponse.of(merchantRefUuid, PayOutStatus.FAILED);
                                            });
                                } else {
                                    return Mono.just(PayOutResponse.of(merchantRefUuid, PayOutStatus.FAILED));
                                }
                            });
                }).onErrorResume(throwable -> {
                    log.error(throwable.toString(), throwable);
                    return Mono.just(PayOutResponse.of(merchantRefUuid, PayOutStatus.FAILED));
                });
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
