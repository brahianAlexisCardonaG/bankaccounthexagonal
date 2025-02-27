package com.proyect.bankaccount.application.usecase.account;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.ports.in.account.CreateAccountUseCase;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;

import java.util.ArrayList;
import java.util.Optional;

public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    private final ClientRepositoryPort clientRepositoryPort;

    public CreateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort
                                    , ClientRepositoryPort clientRepositoryPort
    ) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Account createAccount(Account account) {
        if (account.getClient() == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        Optional<Account> existingAccount = accountRepositoryPort.findByAccountNumber(account.getAccountNumber());
        if (existingAccount.isPresent()) {
            throw new IllegalStateException("Error: Ya existe una cuenta con el mismo número");
        }

        Client client = account.getClient();

        Optional<Client> existingClient = clientRepositoryPort.findByIdentificationNumber(client.getIdentificationNumber());

        if (existingClient.isPresent()) {
            client = existingClient.get();
        } else {
            client = clientRepositoryPort.save(client);
        }

        client.setAccount(new ArrayList<>());

        account.setClient(client);

        return accountRepositoryPort.save(account);
    }
}
