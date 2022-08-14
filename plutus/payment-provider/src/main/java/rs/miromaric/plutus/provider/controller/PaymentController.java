package rs.miromaric.plutus.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.miromaric.plutus.provider.model.credit.CreditRequest;
import rs.miromaric.plutus.provider.model.credit.CreditResponse;
import rs.miromaric.plutus.provider.model.credit.CreditStatus;
import rs.miromaric.plutus.provider.model.credit.ImmutableCreditResponse;
import rs.miromaric.plutus.provider.model.debit.DebitRequest;
import rs.miromaric.plutus.provider.model.debit.DebitResponse;
import rs.miromaric.plutus.provider.model.debit.DebitStatus;
import rs.miromaric.plutus.provider.model.debit.ImmutableDebitResponse;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
@Slf4j
public class PaymentController {

    @PostMapping("/debit")
    public DebitResponse debit(@RequestBody DebitRequest debitRequest) {
        log.debug(debitRequest.toString());
        return ImmutableDebitResponse
                .builder()
                .providerRefNum(UUID.randomUUID().toString())
                .time(new Date())
                .status(DebitStatus.COMPLETED)
                .build();
    }

    @PostMapping("/credit")
    public CreditResponse credit(@RequestBody CreditRequest creditRequest) {
        log.debug(creditRequest.toString());
        return ImmutableCreditResponse
                .builder()
                .providerRefNum(UUID.randomUUID().toString())
                .time(new Date())
                .status(CreditStatus.COMPLETED)
                .build();
    }


}
