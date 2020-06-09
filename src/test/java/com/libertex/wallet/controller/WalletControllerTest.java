package com.libertex.wallet.controller;

import com.libertex.wallet.entity.Client;
import com.libertex.wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WalletController.class)
public class WalletControllerTest extends BaseControllerTest {
    @MockBean
    private WalletService walletService;

    @Test
    void testGetWalletStatusByClientId() throws Exception {
        when(walletService.getWalletStatusByClientId(1L)).thenReturn(new BigDecimal("101"));

        mvc.perform(MockMvcRequestBuilders.get("/wallet/1")
                .secure(false)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> new BigDecimal("101").equals(result));
    }

    @Test
    void testAddToWalletByClientId() throws Exception {

        Client client = createClient(1L, "TestClient", new BigDecimal("103"));
        when(walletService.addToWalletByClientId(1L, new BigDecimal("2")))
                .thenReturn(client);
        String clientJSON = objectMapper.writeValueAsString(client);

//        mvc.perform(MockMvcRequestBuilders.put("/wallet/1/add"))


        mvc.perform(MockMvcRequestBuilders.put("/wallet/1/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(2))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> clientJSON.equals(result));
    }

    @Test
    void testSubFromWalletByClientId() throws Exception {

        Client client = createClient(1L, "TestClient", new BigDecimal("99"));
        when(walletService.addToWalletByClientId(1L, new BigDecimal("2")))
                .thenReturn(client);
        String clientJSON = objectMapper.writeValueAsString(client);


        mvc.perform(MockMvcRequestBuilders.put("/wallet/1/sub")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(2))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> clientJSON.equals(result));
    }
}

