package rs.miromaric.plutus.wallet.service;

import reactor.core.publisher.Mono;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;

public interface WalletService {
    Mono<Wallet> save(Wallet wallet);
    Mono<Wallet> get(String uuid);
    Mono<Wallet> update(Wallet wallet);
    Mono<Wallet> delete(String uuid);
    Mono<WPayInResponse> payIn(WPayInRequest request);
    Mono<WPayOutResponse> payOut(WPayOutRequest request);
}
