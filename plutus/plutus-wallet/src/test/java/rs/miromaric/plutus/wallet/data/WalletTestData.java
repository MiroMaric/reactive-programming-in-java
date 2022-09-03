package rs.miromaric.plutus.wallet.data;

import rs.miromaric.plutus.user.model.ImmutableSystemUser;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.wallet.model.ImmutableWallet;
import rs.miromaric.plutus.wallet.model.Wallet;

import java.math.BigDecimal;

public class WalletTestData {

    public static final Wallet MIRO_WALLET = ImmutableWallet
            .builder()
            .uuid("90217c88-64b9-4661-94b8-5b51b6df877e")
            .userUuid("67488354-231a-46e0-8b98-6d9b4e21325f")
            .label("Miro Wallet")
            .currency("RSD")
            .build();

    public static final SystemUser MIRO = ImmutableSystemUser.builder()
            .uuid("67488354-231a-46e0-8b98-6d9b4e21325f")
            .firstname("Miro")
            .lastname("Maric")
            .username("miro")
            .isActive(true)
            .build();

}
