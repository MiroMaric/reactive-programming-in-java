package rs.miromaric.plutus.payment.service;

import reactor.core.publisher.Mono;
import rs.miromaric.plutus.payment.model.payin.PayInRequest;
import rs.miromaric.plutus.payment.model.payin.PayInResponse;
import rs.miromaric.plutus.payment.model.payout.PayOutRequest;
import rs.miromaric.plutus.payment.model.payout.PayOutResponse;

public interface PaymentService {
    Mono<PayInResponse> payIn(PayInRequest payInRequest);
    Mono<PayOutResponse> payOut(PayOutRequest payOutRequest);
}
