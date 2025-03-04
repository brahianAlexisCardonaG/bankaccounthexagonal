package com.proyect.bankaccount.application.service;

import com.proyect.bankaccount.domain.ports.in.client.CreateClientUseCase;
import com.proyect.bankaccount.domain.ports.in.client.GetClientUseCase;
import com.proyect.bankaccount.domain.model.client.Client;

import java.util.List;
import java.util.Optional;

public class ClientService implements CreateClientUseCase, GetClientUseCase {

    private final CreateClientUseCase createClientUseCase;
    private final GetClientUseCase getClientUseCase;

    private final MessageService messageService;


    public ClientService(CreateClientUseCase createClientUseCase
            , GetClientUseCase getClientUseCase,

                         MessageService messageService) {
        this.createClientUseCase = createClientUseCase;
        this.getClientUseCase = getClientUseCase;
        this.messageService = messageService;
    }

    @Override
    public Client createClient(Client client) {
        return createClientUseCase.createClient(client);
    }

    @Override
    public List<Client> getAllClient() {
        return getClientUseCase.getAllClient();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return getClientUseCase.getClientById(id);
    }

    @Override
    public Optional<Client> findByIdentificationNumber(String identificationNumber) {
        return getClientUseCase.findByIdentificationNumber(identificationNumber);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return getClientUseCase.findByEmail(email);
    }
}
