package com.proyect.bankaccount.application.usecase.client;

import com.proyect.bankaccount.application.service.MessageService;
import com.proyect.bankaccount.domain.exception.GeneralNotFoundException;
import com.proyect.bankaccount.domain.ports.in.client.GetClientUseCase;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import com.proyect.bankaccount.domain.model.client.Client;

import java.util.List;
import java.util.Optional;

public class GetClientUseCaseImpl implements GetClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    private final MessageService messageService;

    public GetClientUseCaseImpl(ClientRepositoryPort clientRepositoryPort, MessageService messageService) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.messageService = messageService;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clientList = clientRepositoryPort.findAll();
        if (clientList.isEmpty()){
            throw new GeneralNotFoundException(messageService.getMessage("error.client.code.not.found"));
        }
        return clientList;
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        Optional<Client> clientOptional = clientRepositoryPort.findById(id);
        if(!clientOptional.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.client.not.found.id",id));
        }
        return clientOptional;
    }

    @Override
    public Optional<Client> findByIdentificationNumber(String identificationNumber) {
        Optional<Client> clientOptional = clientRepositoryPort.findByIdentificationNumber(identificationNumber);
        if(!clientOptional.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.client.code.not.exist",identificationNumber));
        }
        return clientOptional;
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        Optional<Client> clientOptional = clientRepositoryPort.findByEmail(email);
        if(!clientOptional.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.client.code.email.already.exist",email));
        }
        return clientOptional;
    }
}
