package rs.miromaric.plutus.wallet.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;
import rs.miromaric.plutus.wallet.service.WalletService;

import java.math.BigDecimal;

import static rs.miromaric.plutus.wallet.data.WalletTestData.MIRO_WALLET;

@WebFluxTest
@Import({ WalletApiEndpointConfiguration.class, WalletApiHandler.class})
public class WalletApiTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private WalletService service;

    @BeforeEach
    public void beforeEach() {
        Mockito.when(service.save(MIRO_WALLET)).thenReturn(Mono.just(MIRO_WALLET));
        Mockito.when(service.get(MIRO_WALLET.getUuid())).thenReturn(Mono.just(MIRO_WALLET));
        Mockito.when(service.update(MIRO_WALLET)).thenReturn(Mono.just(MIRO_WALLET));
        Mockito.when(service.delete(MIRO_WALLET.getUuid())).thenReturn(Mono.just(MIRO_WALLET));
    }

    private String getUri() {
        return "/fn/api/v1/wallets/";
    }

    @Test
    public void save() {
        Flux<Wallet> response = this.client.post()
                .uri(getUri())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(MIRO_WALLET))
                .exchange()
                .expectHeader().exists(HttpHeaders.LOCATION)
                .expectStatus().isCreated()
                .returnResult(Wallet.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIRO_WALLET::equals)
                .verifyComplete();
    }

    @Test
    public void get() {
        Flux<Wallet> response = this.client.get()
                .uri(getUri() + MIRO_WALLET.getUuid())
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(Wallet.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIRO_WALLET::equals)
                .verifyComplete();
    }

    @Test
    public void delete() {
        Flux<Wallet> response = this.client.delete()
                .uri(getUri() + MIRO_WALLET.getUuid())
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(Wallet.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIRO_WALLET::equals)
                .verifyComplete();
    }

    @Test
    public void update() {
        Flux<Wallet> response = this.client.put()
                .uri(getUri() + MIRO_WALLET.getUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(MIRO_WALLET))
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(Wallet.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIRO_WALLET::equals)
                .verifyComplete();
    }

    @Test
    public void payIn() {
        WPayInRequest request = WPayInRequest.of(MIRO_WALLET.getUuid(), BigDecimal.TEN);
        WPayInResponse response = WPayInResponse.of(MIRO_WALLET, WPayInStatus.COMPLETED);
        Mockito.when(service.payIn(request)).thenReturn(Mono.just(response));

        Flux<WPayInResponse> apiResponse = this.client.put()
                .uri(getUri() + request.getWalletUuid() + "/pay-in")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(WPayInResponse.class)
                .getResponseBody();

        StepVerifier.create(apiResponse)
                .expectNextMatches(response::equals)
                .verifyComplete();
    }

    @Test
    public void payOut() {
        WPayOutRequest request = WPayOutRequest.of(MIRO_WALLET.getUuid(), BigDecimal.TEN);
        WPayOutResponse response = WPayOutResponse.of(MIRO_WALLET, WPayOutStatus.COMPLETED);
        Mockito.when(service.payOut(request)).thenReturn(Mono.just(response));

        Flux<WPayOutResponse> apiResponse = this.client.put()
                .uri(getUri() + request.getWalletUuid() + "/pay-out")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(WPayOutResponse.class)
                .getResponseBody();

        StepVerifier.create(apiResponse)
                .expectNextMatches(response::equals)
                .verifyComplete();
    }

}
