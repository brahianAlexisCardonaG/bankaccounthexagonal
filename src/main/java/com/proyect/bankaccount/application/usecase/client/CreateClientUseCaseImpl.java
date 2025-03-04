package com.proyect.bankaccount.application.usecase.client;

import com.proyect.bankaccount.application.service.MessageService;
import com.proyect.bankaccount.domain.exception.GeneralNotFoundException;
import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.domain.ports.in.client.CreateClientUseCase;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;

import java.time.LocalDate;
import java.util.Optional;

public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    private final MessageService messageService;

    public CreateClientUseCaseImpl(ClientRepositoryPort clientRepositoryPort, MessageService messageService) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.messageService = messageService;
    }

    @Override
    public Client createClient(Client client) {
        Optional<Client> clientOptionalIdentification = clientRepositoryPort
                .findByIdentificationNumber(client.getIdentificationNumber());

        Optional<Client> clientOptionalEmail = clientRepositoryPort
                .findByEmail(client.getEmail());

        if (clientOptionalIdentification.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.client.code.already.exist",client.getIdentificationNumber()));
        }else if(clientOptionalEmail.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.client.code.email.already.exist",client.getEmail()));
        }else{

            client.setCreatedAt(LocalDate.now());
            return clientRepositoryPort.save(client);
        }
    }
}
