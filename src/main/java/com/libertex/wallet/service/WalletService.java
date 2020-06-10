package com.libertex.wallet.service;

import com.libertex.wallet.dto.WalletDto;
import com.libertex.wallet.entity.WalletEntity;
import com.libertex.wallet.repository.WalletRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Log4j2
@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> addMoney = (wallet, money) -> wallet.add(money);
    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> subMoney = (wallet, money) -> wallet.subtract(money);

    public BigDecimal getWalletMoneyByClientId(final Long clientId) {
        return walletRepository.findById(clientId).get().getMoney();
    }

    public WalletDto addToWallet(final WalletDto walletDto) {
        return modifyWalletByClientId(walletDto, addMoney);
    }

    public WalletDto subFromWallet(final WalletDto walletDto) {
        return modifyWalletByClientId(walletDto, subMoney);
    }

    private WalletDto modifyWalletByClientId(final WalletDto walletDto, final BiFunction operation) {
        WalletEntity wallet = walletRepository.findByClient_Id(walletDto.getIdClient());
        final BigDecimal result = (BigDecimal) operation.apply(wallet.getMoney(), walletDto.getMoney());
        wallet.setMoney(result);
        walletRepository.save(wallet);
        return convertWalletEntityToDto(wallet);
    }

    private WalletDto convertWalletEntityToDto(WalletEntity walletEntity) {
        WalletDto walletDto = new WalletDto(0L,new BigDecimal(0));
        try {
            BeanUtils.copyProperties(walletEntity, walletDto);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        return walletDto;
    }
}
