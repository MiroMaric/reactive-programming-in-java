package rs.miromaric.plutus.wallet.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import rs.miromaric.plutus.test.common.PlutusPostgreSQLTestContainer;
import rs.miromaric.plutus.wallet.data.WalletDatabaseInitializer;
import rs.miromaric.plutus.wallet.model.ImmutableWallet;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;

import java.math.BigDecimal;

import static rs.miromaric.plutus.wallet.data.WalletTestData.MIRO_WALLET;

@SpringBootTest
@Testcontainers
public class WalletServiceTest {

    @Container
    @SuppressWarnings("unused")
    public static PostgreSQLContainer<?> postgreSQLContainer = PlutusPostgreSQLTestContainer.postgreSQLContainer;

    @Autowired
    WalletDatabaseInitializer walletDatabaseInitializer;

    @Autowired
    WalletService walletService;

    @BeforeEach
    public void beforeEach() {
        StepVerifier.create(walletDatabaseInitializer.resetTables()).verifyComplete();
    }

    @AfterEach
    public void afterEach() {
        StepVerifier.create(walletDatabaseInitializer.clearTables()).verifyComplete();
    }

    @Test
    void testAdd() {
        Mono<Wallet> savedWallet = walletService.save(MIRO_WALLET);
        assertResourceFields(MIRO_WALLET, savedWallet);
    }

    @Test
    void testGet() {
        Mono<Wallet> savedWallet = walletService.save(MIRO_WALLET)
                .flatMap(wallet -> walletService.get(wallet.getUuid()));
        assertResourceFields(MIRO_WALLET, savedWallet);
    }

    @Test
    void testUpdate() {
        Wallet changedWallet = ImmutableWallet.copyOf(MIRO_WALLET).withLabel("MiroM Wallet");

        Mono<Wallet> updatedWallet = walletService.save(MIRO_WALLET)
                .map(wallet -> changedWallet)
                .flatMap(wallet -> walletService.update(wallet));

        assertResourceFields(changedWallet, updatedWallet);
    }

    @Test
    void testDelete() {
        Mono<Wallet> deletedWallet = walletService.save(MIRO_WALLET)
                .flatMap(wallet -> walletService.delete(wallet.getUuid()));
        assertResourceFields(MIRO_WALLET, deletedWallet);

        StepVerifier.create(walletService.delete(MIRO_WALLET.getUuid()))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void testPayIn() {
        BigDecimal payInAmount = BigDecimal.valueOf(10);

        Mono<WPayInResponse> payInResponse = walletService.save(MIRO_WALLET).flatMap(wallet -> {
            WPayInRequest payInRequest = WPayInRequest.of(MIRO_WALLET.getUuid(), payInAmount);
            return walletService.payIn(payInRequest);
        });

        StepVerifier.create(payInResponse)
                .expectNextMatches(response ->
                        response.getStatus().equals(WPayInStatus.COMPLETED) &&
                        MIRO_WALLET.getAmount().add(payInAmount).compareTo(response.getWallet().getAmount()) == 0)
                .verifyComplete();
    }

    @Test
    void testPayInWithInvalidAmount() {
        BigDecimal payInAmount = BigDecimal.valueOf(10);

        Mono<WPayInResponse> payInResponse = walletService.save(MIRO_WALLET).flatMap(wallet -> {
            WPayInRequest negativeAmount = WPayInRequest.of(MIRO_WALLET.getUuid(), payInAmount.negate());
            return walletService.payIn(negativeAmount);
        });

        StepVerifier.create(payInResponse)
                .expectNextMatches(response ->
                        response.getStatus().equals(WPayInStatus.INVALID_AMOUNT) &&
                                MIRO_WALLET.getAmount().compareTo(response.getWallet().getAmount()) == 0)
                .verifyComplete();
    }


    @Test
    void testPayOut() {
        BigDecimal payOutAmount = BigDecimal.valueOf(10);
        Wallet walletWithAmount = MIRO_WALLET.copy().withAmount(payOutAmount);

        Mono<WPayOutResponse> payOutResponse = walletService.save(walletWithAmount)
                .flatMap(wallet -> {
                    WPayOutRequest payOutRequest = WPayOutRequest.of(MIRO_WALLET.getUuid(), payOutAmount);
                    return walletService.payOut(payOutRequest);
                });

        StepVerifier.create(payOutResponse)
                .expectNextMatches(response ->
                        response.getStatus().equals(WPayOutStatus.COMPLETED) &&
                                MIRO_WALLET.getAmount().compareTo(response.getWallet().getAmount()) == 0)
                .verifyComplete();
    }


    @Test
    void testPayOutWithInvalidAmount() {
        BigDecimal payOutAmount = BigDecimal.valueOf(10);

        Mono<WPayOutResponse> payOutResponse = walletService.save(MIRO_WALLET).flatMap(wallet -> {
            WPayOutRequest negativeAmount = WPayOutRequest.of(MIRO_WALLET.getUuid(), payOutAmount.negate());
            return walletService.payOut(negativeAmount);
        });

        StepVerifier.create(payOutResponse)
                .expectNextMatches(response ->
                        response.getStatus().equals(WPayOutStatus.INVALID_AMOUNT) &&
                                MIRO_WALLET.getAmount().compareTo(response.getWallet().getAmount()) == 0)
                .verifyComplete();
    }

    @Test
    void testPayOutWithNotEnoughFunds() {
        Wallet walletWithAmount = MIRO_WALLET.copy().withAmount(BigDecimal.valueOf(5));
        Mono<WPayOutResponse> payOutResponse = walletService.save(walletWithAmount).flatMap(wallet -> {
            WPayOutRequest withInsufficientAmount = WPayOutRequest.of(MIRO_WALLET.getUuid(), BigDecimal.valueOf(10));
            return walletService.payOut(withInsufficientAmount);
        });

        StepVerifier.create(payOutResponse)
                .expectNextMatches(response ->
                        response.getStatus().equals(WPayOutStatus.NOT_ENOUGH_FUNDS) &&
                                walletWithAmount.getAmount().compareTo(response.getWallet().getAmount()) == 0)
                .verifyComplete();
    }

    private void  assertResourceFields(Wallet expected, Mono<Wallet> actual) {
        StepVerifier.create(actual)
                .expectNextMatches(wallet -> wallet.equals(expected))
                .verifyComplete();
    }

}

