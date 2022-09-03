package rs.miromaric.plutus.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transfer(String uuid, String transactionId, String walletUuid, BigDecimal amount, LocalDateTime createdDateTime) {
    public Transfer(String uuid, String transactionId, String walletUuid, BigDecimal amount) {
        this(uuid, transactionId, walletUuid, amount, LocalDateTime.now());
    }
}
