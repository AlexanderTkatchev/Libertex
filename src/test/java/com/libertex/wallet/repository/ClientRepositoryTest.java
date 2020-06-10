package com.libertex.wallet.repository;

import com.libertex.wallet.entity.ClientEntity;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testFindClientById() {
        ClientEntity found = clientRepository.findById(1L).orElse(null);
        assertNotNull(found);
        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getName()).isEqualTo("Husband");
    }

    @Test
    void testNotFindClientById() {
        ClientEntity found = clientRepository.findById(10L).orElse(null);
        assertNull(found);
    }

    @Test
    void testFindAllClients() {
        List<ClientEntity> found = (List<ClientEntity>) clientRepository.findAll();
        assertNotNull(found);
        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0).getId()).isEqualTo(1L);
        assertThat(found.get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testSaveAndUpdateClient() {
        ClientEntity clientBeforeSave = createTestClient("TEST_SAVE");
        ClientEntity clientAfterSave = clientRepository.save(clientBeforeSave);
        assertNotNull(clientAfterSave);
        assertThat(clientAfterSave.getName()).isEqualTo("TEST_SAVE");

        clientAfterSave.setName("TEST_UPDATE");
        ClientEntity clientAfterUpdate = entityManager.merge(clientAfterSave);
        assertNotNull(clientAfterUpdate);
        assertThat(clientAfterUpdate.getName()).isEqualTo("TEST_UPDATE");
    }

    @Test
    void testSaveAndDeleteClient() {
        ClientEntity clientBeforeSave = createTestClient("TEST_SAVE");
        ClientEntity clientAfterSave = clientRepository.save(clientBeforeSave);
        assertNotNull(clientAfterSave);

        ClientEntity found = clientRepository.findById(clientAfterSave.getId()).orElse(null);
        assertNotNull(found);

        clientRepository.deleteById(found.getId());
        ClientEntity foundDeleted = clientRepository.findById(found.getId()).orElse(null);
        assertNull(foundDeleted);
    }

    private ClientEntity createTestClient(final String name) {
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setName(name);
        return client;
    }
}
