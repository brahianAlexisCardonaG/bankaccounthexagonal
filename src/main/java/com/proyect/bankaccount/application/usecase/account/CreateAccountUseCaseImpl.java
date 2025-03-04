package com.proyect.bankaccount.application.usecase.account;

import com.proyect.bankaccount.application.mapper.client.IClientMapperClientBasicClient;
import com.proyect.bankaccount.application.service.MessageService;
import com.proyect.bankaccount.domain.exception.GeneralNotFoundException;
import com.proyect.bankaccount.domain.model.account.Account;
import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.domain.ports.in.account.CreateAccountUseCase;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;

import java.time.LocalDate;
import java.util.Optional;

public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    private final ClientRepositoryPort clientRepositoryPort;

    private final IClientMapperClientBasicClient clientMapperClientBasicClient;

    private MessageService messageService;

    public CreateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort
                                    , ClientRepositoryPort clientRepositoryPort
                                    , MessageService messageService
                                    , IClientMapperClientBasicClient clientMapperClientBasicClient
    ) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
        this.clientMapperClientBasicClient = clientMapperClientBasicClient;
        this.messageService = messageService;
    }

    @Override
    public Account createAccount(Account account) {

        Optional<Account> existingAccount = accountRepositoryPort.findByAccountNumber(account.getAccountNumber());
        if (existingAccount.isPresent()) {
            throw new GeneralNotFoundException(messageService
                    .getMessage("error.account.code.already.exist", account.getAccountNumber()));
        }

        Client client = new Client();

        Optional<Client> existingClient = clientRepositoryPort.findByIdentificationNumber(
                account.getClient().getIdentificationNumber()
        );

        if (existingClient.isPresent()) {
            client = existingClient.get();
            account.setClient(clientMapperClientBasicClient.toClientBasic(client));
        } else {
            client = clientMapperClientBasicClient.toClient(account.getClient());
            Client clientSaved = clientRepositoryPort.save(client);
            clientSaved.setCreatedAt(LocalDate.now());
            account.setClient(clientMapperClientBasicClient.toClientBasic(clientSaved));
        }

        account.setCreatedAt(LocalDate.now());
        return accountRepositoryPort.save(account);
    }
}
