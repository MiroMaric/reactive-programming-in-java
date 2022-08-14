package rs.miromaric.plutus.payment.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rs.miromaric.plutus.provider.model.credit.CreditRequest;
import rs.miromaric.plutus.provider.model.credit.CreditResponse;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;

@FeignClient(name = "payment-provider-service", url = "${plutus.payment.payment-provider-service-url}")
public interface PaymentProviderService {

    @PostMapping("/debit")
    DebitResponse debit(@RequestBody DebitRequest debitRequest);

    @PostMapping("/credit")
    CreditResponse credit(@RequestBody CreditRequest creditRequest);

}
