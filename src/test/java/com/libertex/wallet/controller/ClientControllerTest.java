package com.libertex.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libertex.wallet.dto.ClientDto;
import com.libertex.wallet.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest extends BaseControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllClients() throws Exception {
        List<ClientDto> clientList = createClientList();
        when(clientService.getAll()).thenReturn(clientList);
        String clientListJSON = objectMapper.writeValueAsString(clientList);

        mvc.perform(MockMvcRequestBuilders.get("/client")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> clientListJSON.equals(result));
    }

    @Test
    void testGetClientById() throws Exception {
        when(clientService.findById(1L))
                .thenReturn(createClient(1L, "TestClient"));

        mvc.perform(MockMvcRequestBuilders.get("/client/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("TestClient"));
    }

    @Test
    void testCreateClient() throws Exception {
        ClientDto client = createClient(1L, "TestClient");
        when(clientService.createClient(client)).thenReturn(client);
        String clientJSON = objectMapper.writeValueAsString(client);

        mvc.perform(MockMvcRequestBuilders.post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientJSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(result -> clientJSON.equals(result));
    }

    @Test
    void testUpdateClient() throws Exception {
        ClientDto clientBefore = createClient(1L, "TestClientBefore");
        ClientDto clientAfter = createClient(1L, "TestClientAfter");
        when(clientService.updateClient(clientBefore)).thenReturn(clientAfter);
        String clientBeforeJSON = objectMapper.writeValueAsString(clientBefore);
        String clientAfterJSON = objectMapper.writeValueAsString(clientAfter);

        mvc.perform(MockMvcRequestBuilders.put("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientBeforeJSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> clientAfterJSON.equals(result));
    }
}
