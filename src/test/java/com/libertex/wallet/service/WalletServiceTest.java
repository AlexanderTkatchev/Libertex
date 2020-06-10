package com.libertex.wallet.service;

import com.libertex.wallet.dto.WalletDto;
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
        public WalletService walletService() {
            return new WalletService();
        }
    }

    @Autowired
    private WalletService walletService;

    @Test
    public void testGetWalletStatusByClientId() {
        BigDecimal wallet = walletService.getWalletMoneyByClientId(1L);
        assertThat(wallet).isEqualTo("20.10");
    }

    @Test
    public void testAddToWalletByClientId() {
        WalletDto walletDto = new WalletDto(1L, new BigDecimal("10.1"));
        WalletDto wallet = walletService.addToWallet(walletDto);
        assertNotNull(wallet);
        assertThat(wallet.getMoney()).isEqualTo("30.20");
    }

    @Test
    public void testSubFromWalletByClientId() {
        WalletDto walletDto = new WalletDto(1L, new BigDecimal("10.1"));
        WalletDto wallet = walletService.subFromWallet(walletDto);
        assertNotNull(wallet);
        assertThat(wallet.getMoney()).isEqualTo("10.00");
    }
}
