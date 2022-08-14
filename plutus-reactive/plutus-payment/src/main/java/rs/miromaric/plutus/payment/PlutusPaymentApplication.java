package rs.miromaric.plutus.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class PlutusPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlutusPaymentApplication.class, args);
    }

}
