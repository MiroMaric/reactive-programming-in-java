package rs.miromaric.plutus.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "plutus.payment")
public class PlutusPaymentProperties {
    private String userServiceUrl;
    private String walletServiceUrl;
    private String paymentProviderServiceUrl;

    public String getPaymentProviderServiceUrl() {
        return paymentProviderServiceUrl;
    }

    public String getUserServiceUrl() {
        return userServiceUrl;
    }

    public String getWalletServiceUrl() {
        return walletServiceUrl;
    }

    public void setPaymentProviderServiceUrl(String paymentProviderServiceUrl) {
        this.paymentProviderServiceUrl = paymentProviderServiceUrl;
    }

    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    public void setWalletServiceUrl(String walletServiceUrl) {
        this.walletServiceUrl = walletServiceUrl;
    }
}
