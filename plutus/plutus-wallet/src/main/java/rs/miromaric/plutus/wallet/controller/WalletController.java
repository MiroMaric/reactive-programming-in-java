package rs.miromaric.plutus.wallet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.service.WalletService;

@RestController
@RequestMapping("api/v1/wallets")
@AllArgsConstructor
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public Wallet save(@RequestBody Wallet wallet) {
        return walletService.save(wallet);
    }

    @GetMapping("/{id}")
    public Wallet get(@PathVariable("id") String id) {
        return walletService.get(id);
    }

    @PutMapping("/{id}")
    public Wallet update(@RequestBody Wallet wallet, @PathVariable("id") String id) {
        wallet = wallet.copy().withUuid(id);
        return walletService.update(wallet);
    }

    @DeleteMapping("/{id}")
    public Wallet delete(@PathVariable("id") String id) {
        return walletService.delete(id);
    }

    @PutMapping("/{id}/pay-in")
    public WPayInResponse payIn(@PathVariable("id") String walletUuid, @RequestBody WPayInRequest request) {
        request = request.copy().withWalletUuid(walletUuid);
        return walletService.payIn(request);
    }

    @PutMapping("/{id}/pay-out")
    public WPayOutResponse payOut(@PathVariable("id") String walletUuid, @RequestBody WPayOutRequest request) {
        request = request.copy().withWalletUuid(walletUuid);
        return walletService.payOut(request);
    }

}
