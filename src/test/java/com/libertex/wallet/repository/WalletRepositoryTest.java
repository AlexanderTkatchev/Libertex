package com.libertex.wallet.repository;

import com.libertex.wallet.entity.WalletEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WalletRepositoryTest {
    @Autowired
    private WalletRepository walletRepository;

    @Test
    void testFindWalletByIdClient() {
        WalletEntity found = walletRepository.findByClient_Id(1L);
        assertNotNull(found);
        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getMoney()).isEqualTo(new BigDecimal("20.10"));
    }
}
