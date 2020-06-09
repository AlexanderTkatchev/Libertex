package com.libertex.wallet.repository;

import com.libertex.wallet.entity.Client;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.libertex.wallet.testutils.TestUtils.createTestClient;
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
        Client found = clientRepository.findById(1L).orElse(null);
        assertNotNull(found);
        assertThat(found.getId()).isEqualTo(1L);
        assertThat(found.getName()).isEqualTo("Husband");
        assertThat(found.getWallet()).isEqualByComparingTo("20.10");
    }

    @Test
    void testNotFindClientById() {
        Client found = clientRepository.findById(10L).orElse(null);
        assertNull(found);
    }

    @Test
    void testFindAllClients() {
        List<Client> found = (List<Client>) clientRepository.findAll();
        assertNotNull(found);
        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0).getId()).isEqualTo(1L);
        assertThat(found.get(1).getId()).isEqualTo(2L);
    }

    @Test
    void testSaveAndUpdateClient() {
        Client clientBeforeSave = createTestClient("TEST_SAVE");
        Client clientAfterSave = clientRepository.save(clientBeforeSave);
        assertNotNull(clientAfterSave);
        assertThat(clientAfterSave.getName()).isEqualTo("TEST_SAVE");
        assertThat(clientAfterSave.getWallet()).isEqualByComparingTo("101");

        clientAfterSave.setName("TEST_UPDATE");
        clientAfterSave.setWallet(new BigDecimal(1001));
        Client clientAfterUpdate = entityManager.merge(clientAfterSave);
        assertNotNull(clientAfterUpdate);
        assertThat(clientAfterUpdate.getName()).isEqualTo("TEST_UPDATE");
        assertThat(clientAfterSave.getWallet()).isEqualByComparingTo("1001");
    }

    @Test
    void testSaveAndDeleteClient() {
        Client clientBeforeSave = createTestClient("TEST_SAVE");
        Client clientAfterSave = clientRepository.save(clientBeforeSave);
        assertNotNull(clientAfterSave);

        Client found = clientRepository.findById(clientAfterSave.getId()).orElse(null);
        assertNotNull(found);

        clientRepository.deleteById(found.getId());
        Client foundDeleted = clientRepository.findById(found.getId()).orElse(null);
        assertNull(foundDeleted);
    }
}
