package rs.miromaric.plutus.payment.service.proxy;

import reactor.core.publisher.Mono;
import rs.miromaric.plutus.provider.model.credit.CreditRequest;
import rs.miromaric.plutus.provider.model.credit.CreditResponse;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;

public interface PaymentProviderApi {
    Mono<DebitResponse> debit(DebitRequest debitRequest);
    Mono<CreditResponse> credit(CreditRequest creditRequest);
}
