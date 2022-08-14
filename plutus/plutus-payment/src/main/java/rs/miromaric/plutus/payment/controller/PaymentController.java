package rs.miromaric.plutus.payment.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.miromaric.plutus.payment.model.payin.PayInRequest;
import rs.miromaric.plutus.payment.model.payin.PayInResponse;
import rs.miromaric.plutus.payment.model.payout.PayOutRequest;
import rs.miromaric.plutus.payment.model.payout.PayOutResponse;
import rs.miromaric.plutus.payment.service.PaymentService;

@RestController
@RequestMapping("api/v1/payment")
@AllArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @RequestMapping("/payIn")
    public PayInResponse payIn(@RequestBody PayInRequest payInRequest) {
        return paymentService.payIn(payInRequest);
    }

    @RequestMapping("/payOut")
    public PayOutResponse payOut(@RequestBody PayOutRequest payOutRequest) {
        return paymentService.payOut(payOutRequest);
    }

}
