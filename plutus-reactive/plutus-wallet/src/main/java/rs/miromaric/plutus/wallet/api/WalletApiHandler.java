package rs.miromaric.plutus.wallet.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.ImmutableWPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.ImmutableWPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.service.WalletService;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class WalletApiHandler {

    private final WalletService walletService;


    Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Wallet.class)
                .flatMap(walletService::save)
                .flatMap(wallet -> created(URI.create("/fn/api/v1/wallets/" + wallet.getUuid()))
                        .body(Mono.just(wallet), Wallet.class));
    }

    Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return walletService.get(id)
                .flatMap(wallet -> ok().body(Mono.just(wallet), Wallet.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Wallet.class)
                .flatMap(walletService::update)
                .flatMap(wallet -> ok().body(Mono.just(wallet), Wallet.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return walletService.delete(id)
                .flatMap(wallet -> ok().body(Mono.just(wallet), Wallet.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> payIn(ServerRequest request) {
        return request.bodyToMono(WPayInRequest.class)
                .map(payInRequest -> ImmutableWPayInRequest.copyOf(payInRequest)
                        .withWalletUuid(request.pathVariable("id")))
                .flatMap(walletService::payIn)
                .flatMap(payInResponse -> ok().body(Mono.just(payInResponse), WPayInResponse.class));
    }

    Mono<ServerResponse> payOut(ServerRequest request) {
        return request.bodyToMono(WPayOutRequest.class)
                .map(payOutRequest -> ImmutableWPayOutRequest.copyOf(payOutRequest)
                        .withWalletUuid(request.pathVariable("id")))
                .flatMap(walletService::payOut)
                .flatMap(payOutResponse -> ok().body(Mono.just(payOutResponse), WPayInResponse.class));
    }

}
