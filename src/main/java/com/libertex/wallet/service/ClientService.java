package com.libertex.wallet.service;

import com.libertex.wallet.dto.ClientDto;
import com.libertex.wallet.entity.ClientEntity;
import com.libertex.wallet.repository.ClientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    public List<ClientDto> getAll() {
        List<ClientEntity> clientEntityList = (List<ClientEntity>) clientRepository.findAll();
        return convertClientListEntitiesToDto(clientEntityList);
    }

    public ClientDto findById(final Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElse(new ClientEntity());
        return convertClientEntityToDto(clientEntity);
    }


    public ClientDto createClient(final ClientDto clientDto) {
        ClientEntity clientEntity = convertClientDtoToEntity(clientDto);
        ClientEntity clientEntityResult = clientRepository.save(clientEntity);
        return convertClientEntityToDto(clientEntityResult);
    }

    @Transactional
    public ClientDto updateClient(final ClientDto clientDto) {
        ClientEntity clientEntity = convertClientDtoToEntity(clientDto);
        ClientEntity clientEntityResult = entityManager.merge(clientEntity);
        return convertClientEntityToDto(clientEntityResult);
    }

    public void deleteById(final Long id) {
        clientRepository.deleteById(id);
    }

    private ClientDto convertClientEntityToDto(ClientEntity clientEntity) {
        ClientDto clientDto = new ClientDto(1L, "");
        try {
            BeanUtils.copyProperties(clientEntity, clientDto);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        return clientDto;
    }

    private List<ClientDto> convertClientListEntitiesToDto(List<ClientEntity> clientEntityList) {
        return clientEntityList.stream()
                .map(this::convertClientEntityToDto)
                .collect(Collectors.toList());
    }

    private ClientEntity convertClientDtoToEntity(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        try {
            BeanUtils.copyProperties(clientDto, clientEntity);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        return clientEntity;
    }
}
