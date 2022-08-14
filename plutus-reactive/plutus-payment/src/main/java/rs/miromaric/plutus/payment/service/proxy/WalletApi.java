package rs.miromaric.plutus.payment.service.proxy;

import reactor.core.publisher.Mono;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;

public interface WalletApi {
    Mono<Wallet> get(String id);
    Mono<WPayInResponse> payIn(String walletUuid, WPayInRequest request);
    Mono<WPayOutResponse> payOut(String walletUuid, WPayOutRequest request);
}
