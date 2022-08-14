package rs.miromaric.plutus.wallet.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import rs.miromaric.plutus.wallet.config.PlutusPostgreSQLTestContainer;
import rs.miromaric.plutus.wallet.config.WalletConfig;
import rs.miromaric.plutus.wallet.exception.WalletNotFoundException;
import rs.miromaric.plutus.wallet.model.ImmutableWallet;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static rs.miromaric.plutus.wallet.data.WalletTestData.MIRO_WALLET;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {WalletConfig.class})
@TestPropertySource("classpath:application-test.properties")
public class WalletServiceImplTest {

    @Container
    @SuppressWarnings("unused")
    public static PostgreSQLContainer<?> postgreSQLContainer = PlutusPostgreSQLTestContainer.postgreSQLContainer;

    @Autowired
    WalletService walletService;

    @Test
    void testAdd() {
        Wallet savedWallet = walletService.save(MIRO_WALLET);
        assertNotNull(savedWallet);
        assertResourceFields(MIRO_WALLET, savedWallet);
    }

    @Test
    void testGet() {
        walletService.save(MIRO_WALLET);
        Wallet savedWallet = walletService.get(MIRO_WALLET.getUuid());
        assertResourceFields(MIRO_WALLET, savedWallet);
    }

    @Test
    void testUpdate() {
        walletService.save(MIRO_WALLET);
        Wallet changedWallet = ImmutableWallet.copyOf(MIRO_WALLET).withLabel("MiroM Wallet");
        Wallet updatedWallet = walletService.update(changedWallet);
        assertResourceFields(changedWallet, updatedWallet);
    }

    @Test
    void testDelete() {
        walletService.save(MIRO_WALLET);
        Wallet deletedWallet = walletService.delete(MIRO_WALLET.getUuid());
        assertResourceFields(MIRO_WALLET, deletedWallet);
        assertThrows(WalletNotFoundException.class, () -> walletService.get(MIRO_WALLET.getUuid()));
    }

    @Test
    void testPayIn() {
        walletService.save(MIRO_WALLET);
        BigDecimal payInAmount = BigDecimal.valueOf(10);
        WPayInRequest payInRequest = WPayInRequest.of(MIRO_WALLET.getUuid(), payInAmount);
        WPayInResponse response = walletService.payIn(payInRequest);

        assertEquals(WPayInStatus.COMPLETED, response.getStatus());
        assertEquals(MIRO_WALLET.getAmount().add(payInAmount), response.getWallet().getAmount());
    }

    @Test
    void testPayInWithInvalidAmount() {
        walletService.save(MIRO_WALLET);
        BigDecimal payInAmount = BigDecimal.valueOf(10);

        WPayInRequest negativeAmount = WPayInRequest.of(MIRO_WALLET.getUuid(), payInAmount.negate());
        WPayInResponse response = walletService.payIn(negativeAmount);

        assertEquals(WPayInStatus.INVALID_AMOUNT, response.getStatus());
        assertEquals(MIRO_WALLET.getAmount(), response.getWallet().getAmount());
    }

    @Test
    void testPayOut() {
        BigDecimal payOutAmount = BigDecimal.valueOf(10);
        Wallet walletWithAmount = MIRO_WALLET.copy().withAmount(payOutAmount);
        walletService.save(walletWithAmount);
        WPayOutRequest payOutRequest = WPayOutRequest.of(MIRO_WALLET.getUuid(), payOutAmount);
        WPayOutResponse response = walletService.payOut(payOutRequest);

        assertEquals(WPayOutStatus.COMPLETED, response.getStatus());
        assertEquals(walletWithAmount.getAmount().subtract(payOutAmount), response.getWallet().getAmount());
    }

    @Test
    void testPayOutWithInvalidAmount() {
        walletService.save(MIRO_WALLET);
        BigDecimal payOutAmount = BigDecimal.valueOf(10);

        WPayOutRequest negativeAmount = WPayOutRequest.of(MIRO_WALLET.getUuid(), payOutAmount.negate());
        WPayOutResponse response = walletService.payOut(negativeAmount);

        assertEquals(WPayOutStatus.INVALID_AMOUNT, response.getStatus());
        assertEquals(MIRO_WALLET.getAmount(), response.getWallet().getAmount());
    }

    @Test
    void testPayOutWithNotEnoughFunds() {
        Wallet walletWithAmount = MIRO_WALLET.copy().withAmount(BigDecimal.valueOf(5));
        walletService.save(walletWithAmount);

        WPayOutRequest withInsufficientAmount = WPayOutRequest.of(MIRO_WALLET.getUuid(), BigDecimal.valueOf(10));
        WPayOutResponse response = walletService.payOut(withInsufficientAmount);

        assertEquals(WPayOutStatus.NOT_ENOUGH_FUNDS, response.getStatus());
        assertEquals(walletWithAmount.getAmount(), response.getWallet().getAmount());

    }

    private void assertResourceFields(Wallet expected, Wallet actual) {
        assertEquals(expected.getUuid(), actual.getUuid());
        assertEquals(expected.getUserUuid(), actual.getUserUuid());
        assertEquals(expected.getLabel(), actual.getLabel());
        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.isActive(), actual.isActive());
    }
}
