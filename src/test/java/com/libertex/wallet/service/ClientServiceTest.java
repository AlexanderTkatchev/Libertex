package com.libertex.wallet.service;

import com.libertex.wallet.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientServiceTest {
    @TestConfiguration
    static class ClientServiceTestConfiguration {
        @Bean
        public ClientService clientService() {
            return new ClientService();
        }
    }

    @Autowired
    private ClientService clientService;

    @Test
    public void testGetAll() {
        List<ClientDto> clientList = clientService.getAll();
        assertNotNull(clientList);
        assertThat(clientList.size()).isEqualTo(2);
        assertThat(clientList.get(0).getName()).isEqualTo("Husband");
    }

    @Test
    public void testFindClientById() {
        ClientDto client = clientService.findById(1L);
        assertNotNull(client);
        assertThat(client.getName()).isEqualTo("Husband");
    }

    @Test
    public void testCreateAndUpdateClient() {
        ClientDto clientBeforeSave = createTestClient("TEST_SAVE");
        ClientDto clientAfterSave = clientService.createClient(clientBeforeSave);
        assertNotNull(clientAfterSave);
        assertThat(clientAfterSave.getName()).isEqualTo("TEST_SAVE");

        clientAfterSave.setName("TEST_UPDATE");
        ClientDto clientAfterUpdate = clientService.updateClient(clientAfterSave);
        assertNotNull(clientAfterUpdate);
        assertThat(clientAfterUpdate.getName()).isEqualTo("TEST_UPDATE");
    }

    @Test
    void testCreateAndDeleteClient() {
        ClientDto clientBeforeSave = createTestClient("TEST_SAVE");
        ClientDto clientAfterSave = clientService.createClient(clientBeforeSave);
        assertNotNull(clientAfterSave);

        ClientDto found = clientService.findById(clientAfterSave.getId());
        assertNotNull(found);

        clientService.deleteById(found.getId());
        ClientDto clientAfterDelete = clientService.findById(found.getId());
        assertNull(clientAfterDelete.getId());
    }

    private ClientDto createTestClient(final String name) {
        return new ClientDto(1L, name);
    }
}
