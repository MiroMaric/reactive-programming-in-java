package rs.miromaric.plutus.provider.model;

import java.util.Date;

public interface PaymentResponse {
    String getProviderRefNum();
    Date getTime();
}
