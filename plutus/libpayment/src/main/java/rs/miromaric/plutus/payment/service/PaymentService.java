package rs.miromaric.plutus.payment.service;

import rs.miromaric.plutus.payment.model.payin.PayInRequest;
import rs.miromaric.plutus.payment.model.payin.PayInResponse;
import rs.miromaric.plutus.payment.model.payout.PayOutRequest;
import rs.miromaric.plutus.payment.model.payout.PayOutResponse;

public interface PaymentService {
    PayInResponse payIn(PayInRequest payInRequest);
    PayOutResponse payOut(PayOutRequest payOutRequest);
}
