package com.proyect.bankaccount.application.usecase.client;

import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.ports.in.client.GetClientUseCase;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GetClientUseCaseImpl implements GetClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Autowired
    public GetClientUseCaseImpl(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepositoryPort.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepositoryPort.findById(id);
    }

    @Override
    public Optional<Client> findByIdentificationNumber(String identificationNumber) {
        return clientRepositoryPort.findByIdentificationNumber(identificationNumber);
    }
}
