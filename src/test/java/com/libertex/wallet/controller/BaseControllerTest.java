package com.libertex.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libertex.wallet.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    Client createClient(final Long clientId, final String name, final BigDecimal wallet) {
        Client client = new Client();
        client.setId(clientId);
        client.setWallet(wallet);
        client.setName(name);
        return client;
    }

    List<Client> createClientList() {
        List<Client> clientList = new ArrayList<>();
        for (int i=1; i<3; i++) {
            clientList.add(createClient((long) i, "Name" + i, new BigDecimal(5 * i + i)));
        }
        return clientList;
    }
}
