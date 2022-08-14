package rs.miromaric.plutus.provider.model;

import java.math.BigDecimal;

public interface PaymentRequest {
    String getAccountId();
    String getMerchantRefNum();
    String getToken();
    BigDecimal getAmount();
}
