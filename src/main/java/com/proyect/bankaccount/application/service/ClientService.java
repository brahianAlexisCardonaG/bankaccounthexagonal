package com.proyect.bankaccount.application.service;

import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.ports.in.client.CreateClientUseCase;
import com.proyect.bankaccount.domain.ports.in.client.GetClientUseCase;

import java.util.List;
import java.util.Optional;

public class ClientService implements CreateClientUseCase, GetClientUseCase {

    private final CreateClientUseCase createClientUseCase;
    private final GetClientUseCase getClientUseCase;


    public ClientService(CreateClientUseCase createClientUseCase
            , GetClientUseCase getClientUseCase
    ) {
        this.createClientUseCase = createClientUseCase;
        this.getClientUseCase = getClientUseCase;
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
}
