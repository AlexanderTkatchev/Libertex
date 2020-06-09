package com.libertex.wallet.controller;

import com.libertex.wallet.entity.Client;
import com.libertex.wallet.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@Api(value = "ClientController", tags = {"Operations with clients"})
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    @ApiOperation(value = "List of all clients", response = ArrayList.class)
    public List<Client> getAllClients() {
        log.debug("List of all clients controller");
        return clientService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get client info by ID", response = Client.class)
    public Client getClientById(@PathVariable final Long id) {
        log.debug("Get client info by ID = " + id);
        return clientService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Create client", response = Client.class)
    public Client createClient(@Valid @RequestBody final Client client) {
        log.debug("Create client ID=" + client.getId() + " NAME=" + client.getName() + " WALLET=" + client.getWallet());
        return clientService.createClient(client);
    }

    @PutMapping
    @ApiOperation(value = "Update client info", response = Client.class)
    public Client updateClient(@Valid @RequestBody final Client client) {
        log.debug("Update client ID=" + client.getId() + " NAME=" + client.getName() + " WALLET=" + client.getWallet());
        return clientService.updateClient(client);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete client by ID")
    public void deleteById(@PathVariable final Long id) {
        log.debug("Get client info by ID = " + id);
        clientService.deleteById(id);
    }
}
