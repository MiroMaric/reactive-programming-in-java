package rs.miromaric.plutus.payment.service.proxy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.payment.service.proxy.WalletApi;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;

import java.util.Map;

@Component
@Slf4j
public class WalletApiImpl implements WalletApi {

    private final WebClient webClient;

    public WalletApiImpl(WebClient.Builder webClientBuilder,
                         @Value("${plutus.payment.wallet-service-url}") String userServiceUrl) {
        this.webClient = webClientBuilder
                .baseUrl(userServiceUrl)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Mono<Wallet> get(String id) {
        return webClient.get()
                .uri("/{id}", Map.of("id", id))
                .retrieve()
                .bodyToMono(Wallet.class);
    }

    @Override
    public Mono<WPayInResponse> payIn(String walletUuid, WPayInRequest request) {
        return webClient.put()
                .uri("/{id}/pay-in", Map.of("id", walletUuid))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WPayInResponse.class);
    }

    @Override
    public Mono<WPayOutResponse> payOut(String walletUuid, WPayOutRequest request) {
        return webClient.put()
                .uri("/{id}/pay-out", Map.of("id", walletUuid))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WPayOutResponse.class);
    }
}
