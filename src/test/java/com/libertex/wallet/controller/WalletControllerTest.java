package com.libertex.wallet.controller;

import com.libertex.wallet.dto.WalletDto;
import com.libertex.wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WalletController.class)
public class WalletControllerTest extends BaseControllerTest {
    @MockBean
    private WalletService walletService;

    @Test
    void testGetWalletStatusByClientId() throws Exception {
        when(walletService.getWalletMoneyByClientId(1L)).thenReturn(new BigDecimal("101"));

        mvc.perform(MockMvcRequestBuilders.get("/wallet/1")
                .secure(false)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> new BigDecimal("101").equals(result));
    }

    @Test
    void testAddToWallet() throws Exception {
        WalletDto walletDto = new WalletDto(1L, new BigDecimal("2"));
        when(walletService.addToWallet(any()))
                .thenReturn(walletDto);
        String clientJSON = objectMapper.writeValueAsString(walletDto);

        mvc.perform(MockMvcRequestBuilders.put("/wallet/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(walletDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> clientJSON.equals(result));
    }

    @Test
    void testSubFromWalletByClientId() throws Exception {
        WalletDto walletDto = new WalletDto(1L, new BigDecimal("99"));
        when(walletService.addToWallet(any()))
                .thenReturn(walletDto);
        String clientJSON = objectMapper.writeValueAsString(walletDto);

        mvc.perform(MockMvcRequestBuilders.put("/wallet/sub")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(walletDto))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> clientJSON.equals(result));
    }
}
