package com.libertex.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libertex.wallet.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    ClientDto createClient(final Long clientId, final String name) {
        return new ClientDto(clientId, name);
    }

    List<ClientDto> createClientList() {
        List<ClientDto> clientList = new ArrayList<>();
        for (int i=1; i<3; i++) {
            clientList.add(createClient((long) i, "Name" + i));
        }
        return clientList;
    }
}
