package rs.miromaric.plutus.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rs.miromaric.plutus.wallet.config.PlutusCommonTestConfig;
import rs.miromaric.plutus.wallet.model.Wallet;
import rs.miromaric.plutus.wallet.model.payin.WPayInRequest;
import rs.miromaric.plutus.wallet.model.payin.WPayInResponse;
import rs.miromaric.plutus.wallet.model.payin.WPayInStatus;
import rs.miromaric.plutus.wallet.model.payout.WPayOutRequest;
import rs.miromaric.plutus.wallet.model.payout.WPayOutResponse;
import rs.miromaric.plutus.wallet.model.payout.WPayOutStatus;
import rs.miromaric.plutus.wallet.service.WalletService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rs.miromaric.plutus.wallet.data.WalletTestData.MIRO_WALLET;

@WebMvcTest(WalletController.class)
@Import(PlutusCommonTestConfig.class)
public class WalletControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    WalletService walletService;

    @Test
    public void saveWallet() throws Exception {
        when(walletService.save(MIRO_WALLET)).thenReturn(MIRO_WALLET);
        mockMvc.perform(post("/api/v1/wallets/")
                                .content(mapper.writeValueAsString(MIRO_WALLET))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(MIRO_WALLET), false));
    }


    @Test
    public void getWallet() throws Exception {
        when(walletService.get(MIRO_WALLET.getUuid())).thenReturn(MIRO_WALLET);
        mockMvc.perform(get("/api/v1/wallets/".concat(MIRO_WALLET.getUuid()))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(MIRO_WALLET), false));
    }

    @Test
    public void updateWallet() throws Exception {
        Wallet updatedWallet = MIRO_WALLET.copy().withLabel("MiroM Wallet");
        when(walletService.update(updatedWallet)).thenReturn(updatedWallet);
        mockMvc.perform(put("/api/v1/wallets/".concat(updatedWallet.getUuid()))
                                .content(mapper.writeValueAsString(updatedWallet))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(updatedWallet), false));
    }

    @Test
    public void deleteWallet() throws Exception {
        when(walletService.delete(MIRO_WALLET.getUuid())).thenReturn(MIRO_WALLET);
        mockMvc.perform(delete("/api/v1/wallets/".concat(MIRO_WALLET.getUuid()))
                                .content(mapper.writeValueAsString(MIRO_WALLET))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(MIRO_WALLET), false));
    }

    @Test
    public void payIn() throws Exception {
        WPayInRequest request = WPayInRequest.of(MIRO_WALLET.getUuid(), BigDecimal.TEN);
        WPayInResponse response = WPayInResponse.of(MIRO_WALLET, WPayInStatus.COMPLETED);
        when(walletService.payIn(request)).thenReturn(response);
        mockMvc.perform(put("/api/v1/wallets/".concat(MIRO_WALLET.getUuid()).concat("/pay-in"))
                                .content(mapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(response), false));
    }

    @Test
    public void payOut() throws Exception {
        WPayOutRequest request = WPayOutRequest.of(MIRO_WALLET.getUuid(), BigDecimal.TEN);
        WPayOutResponse response = WPayOutResponse.of(MIRO_WALLET, WPayOutStatus.COMPLETED);
        when(walletService.payOut(request)).thenReturn(response);
        mockMvc.perform(put("/api/v1/wallets/".concat(MIRO_WALLET.getUuid()).concat("/pay-out"))
                                .content(mapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(mapper.writeValueAsString(response), false));
    }

}
