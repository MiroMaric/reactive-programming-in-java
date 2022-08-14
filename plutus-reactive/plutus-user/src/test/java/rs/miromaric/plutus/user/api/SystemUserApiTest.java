package rs.miromaric.plutus.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.service.SystemUserService;

import static rs.miromaric.plutus.user.data.SystemUserTestData.MIKO;
import static rs.miromaric.plutus.user.data.SystemUserTestData.PERA;

public abstract class SystemUserApiTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private SystemUserService service;

    @BeforeEach
    public void beforeEach() {
        Mockito.when(service.save(MIKO)).thenReturn(Mono.just(MIKO));
        Mockito.when(service.save(PERA)).thenReturn(Mono.just(PERA));
        Mockito.when(service.get(MIKO.getUuid())).thenReturn(Mono.just(MIKO));
        Mockito.when(service.get(PERA.getUuid())).thenReturn(Mono.just(PERA));
        Mockito.when(service.update(MIKO)).thenReturn(Mono.just(MIKO));
        Mockito.when(service.delete(MIKO.getUuid())).thenReturn(Mono.just(MIKO));
    }

    protected abstract String getUri();

    @Test
    public void save() {
        Flux<SystemUser> response = this.client.post()
                .uri(getUri())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(MIKO))
                .exchange()
                .expectHeader().exists(HttpHeaders.LOCATION)
                .expectStatus().isCreated()
                .returnResult(SystemUser.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIKO::equals)
                .verifyComplete();
    }

    @Test
    public void get() {
        Flux<SystemUser> response = this.client.get()
                .uri(getUri() + MIKO.getUuid())
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(SystemUser.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIKO::equals)
                .verifyComplete();
    }

    @Test
    public void delete() {
        Flux<SystemUser> response = this.client.delete()
                .uri(getUri() + MIKO.getUuid())
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(SystemUser.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIKO::equals)
                .verifyComplete();
    }

    @Test
    public void update() {
        Flux<SystemUser> response = this.client.put()
                .uri(getUri() + MIKO.getUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(MIKO))
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .returnResult(SystemUser.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectNextMatches(MIKO::equals)
                .verifyComplete();
    }
}
