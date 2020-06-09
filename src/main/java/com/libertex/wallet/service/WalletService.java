package com.libertex.wallet.service;

import com.libertex.wallet.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Service
public class WalletService {
    @Autowired
    private ClientService clientService;

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> addMoney = (wallet, money) -> wallet.add(money);
    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> subMoney = (wallet, money) -> wallet.subtract(money);

    public BigDecimal getWalletStatusByClientId(final Long clientId) {
        return clientService.findById(clientId).getWallet();
    }

    public Client addToWalletByClientId(final Long clientId, final BigDecimal money) {
        return modifyWalletById(clientId, money, addMoney);
    }

    public Client subFromWalletByClientId(final Long clientId, final BigDecimal money) {
        return modifyWalletById(clientId, money, subMoney);
    }

    private Client modifyWalletById(final Long clientId, final BigDecimal money, final BiFunction operation) {
        Client client = clientService.findById(clientId);
        final BigDecimal result = (BigDecimal) operation.apply(client.getWallet(), money);
        client.setWallet(result);
        return clientService.updateClient(client);
    }
}
