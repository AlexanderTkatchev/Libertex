package com.libertex.wallet.service;

import com.libertex.wallet.entity.Client;
import org.junit.jupiter.api.Test;;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WalletServiceTest {
    @TestConfiguration
    static class WalletServiceTestConfiguration {
        @Bean
        public ClientService clientService() {
            return new ClientService();
        }

        @Bean
        public WalletService walletService() {
            return new WalletService();
        }
    }

    @Autowired
    private ClientService clientService;

    @Autowired
    private WalletService walletService;

    @Test
    public void testGetWalletStatusByClientId() {
        BigDecimal wallet = walletService.getWalletStatusByClientId(1L);
        assertThat(wallet).isEqualTo("20.10");
    }

    @Test
    public void testAddToWalletByClientId() {
        Client client = walletService.addToWalletByClientId(1L, new BigDecimal("10.1"));
        assertNotNull(client);
        assertThat(client.getWallet()).isEqualTo("30.20");
    }

    @Test
    public void testSubFromWalletByClientId() {
        Client client = walletService.subFromWalletByClientId(2L, new BigDecimal("1"));
        assertNotNull(client);
        assertThat(client.getWallet()).isEqualTo("9999.55");
    }
}
