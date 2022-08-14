package rs.miromaric.plutus.payment.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.payment.model.payin.PayInRequest;
import rs.miromaric.plutus.payment.model.payin.PayInResponse;
import rs.miromaric.plutus.payment.model.payout.PayOutRequest;
import rs.miromaric.plutus.payment.model.payout.PayOutResponse;
import rs.miromaric.plutus.payment.service.PaymentService;

@Component
@RequiredArgsConstructor
public class PaymentApiHandler {

    private final PaymentService paymentService;

    Mono<ServerResponse> payIn(ServerRequest request) {
        return request.bodyToMono(PayInRequest.class)
                .flatMap(payInRequest -> ServerResponse.ok()
                        .body(paymentService.payIn(payInRequest), PayInResponse.class));
    }

    Mono<ServerResponse> payOut(ServerRequest request) {
        return request.bodyToMono(PayOutRequest.class)
                .flatMap(payOutRequest -> ServerResponse.ok()
                        .body(paymentService.payOut(payOutRequest), PayOutResponse.class));
    }

}
