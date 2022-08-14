package rs.miromaric.plutus.payment.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;

@FeignClient(name = "wallet-service", url = "${plutus.payment.wallet-service-url}")
public interface WalletService {

    @GetMapping("/{id}")
    Wallet get(@PathVariable("id") String id);

    @PutMapping("/{id}/pay-in")
    WPayInResponse payIn(@PathVariable("id") String walletUuid, @RequestBody WPayInRequest request);

    @PutMapping("/{id}/pay-out")
    WPayOutResponse payOut(@PathVariable("id") String walletUuid, @RequestBody WPayOutRequest request);

}
