package rs.miromaric.plutus.payment.entity;

import lombok.Getter;
import lombok.Setter;
import rs.miromaric.plutus.common.entity.PlutusEntity;
import rs.miromaric.plutus.common.entity.PlutusEntityId;
import rs.miromaric.plutus.payment.model.Transfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSFER")
@Getter
@Setter
public class TransferEntity extends PlutusEntityId implements PlutusEntity<Transfer>  {
    @Column(name = "TRANSACTION_ID", nullable = false, updatable = false)
    private String transactionId;
    @Column(name = "WALLET_UUID", nullable = false, updatable = false)
    private String walletUuid;
    @Column(name = "AMOUNT", nullable = false, updatable = false)
    private BigDecimal amount;
    @Column(name = "CREATED_DATETIME", nullable = false, updatable = false)
    private LocalDateTime createdDateTime = LocalDateTime.now();

    public TransferEntity(Transfer defaultDto) {
        this.uuid = defaultDto.uuid();
        this.transactionId = defaultDto.transactionId();
        this.walletUuid = defaultDto.walletUuid();
        this.amount = defaultDto.amount();
        this.createdDateTime = defaultDto.createdDateTime();
    }

    @SuppressWarnings("unused")
    public TransferEntity() { }

    @Override
    public PlutusEntity<Transfer> update(Transfer defaultDto) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Transfer getDefaultDto() {
        return new Transfer(uuid, transactionId, walletUuid, amount, createdDateTime);
    }
}
