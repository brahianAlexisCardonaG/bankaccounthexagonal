package com.proyect.bankaccount.infraestructure.repositories;

import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import com.proyect.bankaccount.infraestructure.entities.AccountEntity;
import com.proyect.bankaccount.infraestructure.entities.ClientEntity;
import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.infraestructure.mapper.IClientMapperEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaClientRepositoryAdapter implements ClientRepositoryPort {

    private final JpaClientRepository jpaClientRepository;


    private final IClientMapperEntityDomain clientMapperEntityDomaintMapper;

    @Autowired
    public JpaClientRepositoryAdapter(JpaClientRepository jpaClientRepository, IClientMapperEntityDomain clientMapperEntityDomaintMapper){
        this.clientMapperEntityDomaintMapper = clientMapperEntityDomaintMapper;
        this.jpaClientRepository =jpaClientRepository;
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<ClientEntity> findClient = jpaClientRepository.findById(id);
        return findClient.map(clientMapperEntityDomaintMapper::toClient);
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = clientMapperEntityDomaintMapper.toClientEntity(client);
        ClientEntity savedClient = jpaClientRepository.save(clientEntity);
        return clientMapperEntityDomaintMapper.toClient(savedClient);
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> findAllClient = jpaClientRepository.findAll();
        return findAllClient.stream()
                .map(clientMapperEntityDomaintMapper::toClient)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findByIdentificationNumber(String identificationNumber) {
        Optional<ClientEntity> findClient = jpaClientRepository.findByIdentificationNumber(identificationNumber);
        return findClient.map(clientMapperEntityDomaintMapper::toClient);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        Optional<ClientEntity> clientOptional = jpaClientRepository.findByEmail(email);
        return clientOptional.map(clientMapperEntityDomaintMapper::toClient);
    }
}
