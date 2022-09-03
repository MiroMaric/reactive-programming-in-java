package rs.miromaric.plutus.wallet.entity;

import lombok.Getter;
import lombok.Setter;
import rs.miromaric.plutus.common.entity.PlutusEntity;
import rs.miromaric.plutus.common.entity.VersionedPlutusEntity;
import rs.miromaric.plutus.wallet.model.ImmutableWallet;
import rs.miromaric.plutus.wallet.model.Wallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "WALLET", indexes = {
        @Index(name = "wallet_user_idx", columnList = "user_uuid", unique = true)
})
@Getter
@Setter
public class WalletEntity extends VersionedPlutusEntity implements PlutusEntity<Wallet>{

    @Column(name = "USER_UUID", nullable = false)
    private String userUuid;
    @Column(name = "LABEL", nullable = false)
    private String label;
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    @Column(name = "CURRENCY", nullable = false, length = 3)
    private String currency;
    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    public WalletEntity(Wallet defaultDto) {
        this.uuid = defaultDto.getUuid();
        this.amount = defaultDto.getAmount();
        update(defaultDto);
    }

    @SuppressWarnings("unused")
    public WalletEntity() { }

    @Override
    public WalletEntity update(Wallet defaultDto) {
        this.label = defaultDto.getLabel();
        this.userUuid = defaultDto.getUserUuid();
        this.active = defaultDto.isActive();
        this.currency = defaultDto.getCurrency();
        return this;
    }

    @Override
    public Wallet getDefaultDto() {
        return ImmutableWallet
                .builder()
                .uuid(uuid)
                .userUuid(userUuid)
                .label(label)
                .amount(amount)
                .currency(currency)
                .isActive(active)
                .build();
    }
}
