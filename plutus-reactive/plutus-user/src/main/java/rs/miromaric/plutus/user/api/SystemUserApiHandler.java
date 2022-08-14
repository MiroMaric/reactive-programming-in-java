package rs.miromaric.plutus.user.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.service.SystemUserService;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class SystemUserApiHandler {

    private final SystemUserService systemUserService;

    Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(SystemUser.class)
                .flatMap(systemUserService::save)
                .flatMap(systemUser -> created(URI.create("/fn/api/v1/users/" + systemUser.getUuid()))
                        .body(Mono.just(systemUser), SystemUser.class));
    }

    Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return systemUserService.get(id)
                .flatMap(systemUser -> ok().body(Mono.just(systemUser), SystemUser.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(SystemUser.class)
                .flatMap(systemUserService::update)
                .flatMap(systemUser -> ok().body(Mono.just(systemUser), SystemUser.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return systemUserService.delete(id)
                .flatMap(systemUser -> ok().body(Mono.just(systemUser), SystemUser.class))
                .switchIfEmpty(notFound().build());
    }

}
