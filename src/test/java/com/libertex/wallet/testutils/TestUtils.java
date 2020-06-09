package com.libertex.wallet.testutils;

import com.libertex.wallet.entity.Client;

import java.math.BigDecimal;

public class TestUtils {
    public static Client createTestClient(final String name) {
        Client client = new Client();
        client.setId(1L);
        client.setName(name);
        client.setWallet(new BigDecimal(101));
        return client;
    }
}
