package rs.miromaric.plutus.wallet.service;

import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;

public interface WalletService {
    Wallet save(Wallet wallet);
    Wallet get(String uuid);
    Wallet update(Wallet wallet);
    Wallet delete(String uuid);
    WPayInResponse payIn(WPayInRequest request);
    WPayOutResponse payOut(WPayOutRequest request);
}
