package rs.miromaric.plutus.payment.service.proxy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.payment.service.proxy.PaymentProviderApi;
import rs.miromaric.plutus.provider.model.credit.CreditRequest;
import rs.miromaric.plutus.provider.model.credit.CreditResponse;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;

@Component
@Slf4j
public class PaymentProviderApiImpl implements PaymentProviderApi {

    private final WebClient webClient;

    public PaymentProviderApiImpl(WebClient.Builder webClientBuilder,
                                  @Value("${plutus.payment.payment-provider-service-url}") String url) {
        this.webClient = webClientBuilder
                .baseUrl(url)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Mono<DebitResponse> debit(DebitRequest debitRequest) {
        return webClient.post()
                .uri("/debit")
                .bodyValue(debitRequest)
                .retrieve()
                .bodyToMono(DebitResponse.class);
    }

    @Override
    public Mono<CreditResponse> credit(CreditRequest creditRequest) {
        return webClient.post()
                .uri("/credit")
                .bodyValue(creditRequest)
                .retrieve()
                .bodyToMono(CreditResponse.class);
    }
}
