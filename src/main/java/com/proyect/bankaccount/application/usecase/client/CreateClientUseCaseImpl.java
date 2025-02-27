package com.proyect.bankaccount.application.usecase.client;

import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.ports.in.client.CreateClientUseCase;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Autowired
    public CreateClientUseCaseImpl(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepositoryPort.save(client);
    }
}
