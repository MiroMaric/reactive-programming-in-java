package rs.miromaric.plutus.wallet.entity;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import rs.miromaric.plutus.common.entity.PlutusEntity;
import rs.miromaric.plutus.common.entity.VersionedPlutusEntity;
import rs.miromaric.plutus.wallet.model.ImmutableWallet;
import rs.miromaric.plutus.wallet.model.Wallet;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Table("WALLET")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletEntity extends VersionedPlutusEntity implements PlutusEntity<Wallet> {

    @Column("USER_UUID")
    private String userUuid;
    @Column("LABEL")
    private String label;
    @Column("AMOUNT")
    private BigDecimal amount;
    @Column("CURRENCY")
    private String currency;
    @Column("ACTIVE")
    private Boolean active;

    @Transient
    @EqualsAndHashCode.Exclude
    private boolean newEntity;

    @Override
    public String getId() {
        return uuid;
    }

    @Override
    public boolean isNew() {
        return newEntity;
    }

    public WalletEntity(Wallet defaultDto) {
        this.uuid = defaultDto.getUuid();
        this.amount = defaultDto.getAmount();
        this.newEntity = true;
        update(defaultDto);
    }

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
        return ImmutableWallet.builder()
                .uuid(uuid)
                .userUuid(userUuid)
                .label(label)
                .amount(amount.setScale(3, RoundingMode.UNNECESSARY))
                .currency(currency)
                .isActive(active)
                .build();
    }

}
