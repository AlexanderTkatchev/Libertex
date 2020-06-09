package com.libertex.wallet.service;

import com.libertex.wallet.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.libertex.wallet.testutils.TestUtils.createTestClient;
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
        List<Client> clientList = clientService.getAll();
        assertNotNull(clientList);
        assertThat(clientList.size()).isEqualTo(2);
        assertThat(clientList.get(0).getName()).isEqualTo("Husband");
    }

    @Test
    public void testFindClientById() {
        Client client = clientService.findById(1L);
        assertNotNull(client);
        assertThat(client.getName()).isEqualTo("Husband");
    }

    @Test
    public void testCreateAndUpdateClient() {
        Client clientBeforeSave = createTestClient("TEST_SAVE");
        Client clientAfterSave = clientService.createClient(clientBeforeSave);
        assertNotNull(clientAfterSave);
        assertThat(clientAfterSave.getName()).isEqualTo("TEST_SAVE");
        assertThat(clientAfterSave.getWallet()).isEqualByComparingTo(new BigDecimal(101));

        clientAfterSave.setName("TEST_UPDATE");
        clientAfterSave.setWallet(new BigDecimal(1001));
        Client clientAfterUpdate = clientService.updateClient(clientAfterSave);
        assertNotNull(clientAfterUpdate);
        assertThat(clientAfterUpdate.getName()).isEqualTo("TEST_UPDATE");
        assertThat(clientAfterSave.getWallet()).isEqualByComparingTo("1001");
    }

    @Test
    void testCreateAndDeleteClient() {
        Client clientBeforeSave = createTestClient("TEST_SAVE");
        Client clientAfterSave = clientService.createClient(clientBeforeSave);
        assertNotNull(clientAfterSave);

        Client found = clientService.findById(clientAfterSave.getId());
        assertNotNull(found);

        clientService.deleteById(found.getId());
        Client clientAfterDelete = clientService.findById(found.getId());
        assertNull(clientAfterDelete);
    }
}
