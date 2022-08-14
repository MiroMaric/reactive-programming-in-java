package rs.miromaric.plutus.payment.service.proxy.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.payment.service.proxy.SystemUserApi;
import rs.miromaric.plutus.user.model.SystemUser;

import java.util.Map;

@Component
@Slf4j
public class SystemUserApiImpl implements SystemUserApi {

    private final WebClient webClient;

    public SystemUserApiImpl(WebClient.Builder webClientBuilder,
                             @Value("${plutus.payment.user-service-url}") String userServiceUrl) {
        this.webClient = webClientBuilder
                .baseUrl(userServiceUrl)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Mono<SystemUser> get(String id) {
        return webClient.get()
                .uri("/{id}", Map.of("id", id))
                .retrieve()
                .bodyToMono(SystemUser.class);
    }

}
