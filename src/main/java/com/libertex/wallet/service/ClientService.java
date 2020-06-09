package com.libertex.wallet.service;

import com.libertex.wallet.entity.Client;
import com.libertex.wallet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Client> getAll() {
        return (List<Client>) clientRepository.findAll();
    }

    public Client findById(final Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(final Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(final Client client) {
        return entityManager.merge(client);
    }

    public void deleteById(final Long id) {
        clientRepository.deleteById(id);
    }
}
