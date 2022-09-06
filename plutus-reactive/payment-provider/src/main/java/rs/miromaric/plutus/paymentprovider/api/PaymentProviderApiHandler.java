package rs.miromaric.plutus.paymentprovider.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.paymentprovider.stream.sender.DefaultDebitResponseSender;
import rs.miromaric.plutus.provider.model.credit.CreditResponse;
import rs.miromaric.plutus.provider.model.credit.CreditStatus;
import rs.miromaric.plutus.provider.model.credit.ImmutableCreditResponse;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;
import rs.miromaric.plutus.provider.model.debit.ImmutableDebitResponse;

import java.util.Date;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentProviderApiHandler {

    private final DefaultDebitResponseSender newDebitResponseSender;

    Mono<ServerResponse> debit(ServerRequest request) {
        DebitResponse debitResponse = ImmutableDebitResponse
                .builder()
                .providerRefNum(UUID.randomUUID().toString())
                .time(new Date())
                .status(DebitStatus.COMPLETED)
                .build();

        return ok().body(newDebitResponseSender.sendMessage(debitResponse), DebitResponse.class);
    }

    Mono<ServerResponse> credit(ServerRequest request) {
        CreditResponse creditResponse = ImmutableCreditResponse
                .builder()
                .providerRefNum(UUID.randomUUID().toString())
                .time(new Date())
                .status(CreditStatus.COMPLETED)
                .build();

        return ok().body(Mono.just(creditResponse), CreditResponse.class);
    }

}
