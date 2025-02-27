package com.proyect.bankaccount.application.usecase.transaction;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.model.Transaction;
import com.proyect.bankaccount.domain.ports.in.transaction.CreateTransactionUseCase;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.TransactionRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    private final AccountRepositoryPort accountRepositoryPort;

    private final ClientRepositoryPort clientRepositoryPort;

    @Autowired
    public CreateTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort, AccountRepositoryPort accountRepositoryPort, ClientRepositoryPort clientRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Transaction CreateTransaction(Transaction transaction, String identificationNumber, String accountNumber) {

        Optional<Account> existingAccount = accountRepositoryPort.findByAccountNumber(accountNumber);
        Optional<Client> existingClient = clientRepositoryPort.findByIdentificationNumber(identificationNumber);
        if (!existingAccount.isPresent() || !existingClient.isPresent()) {
            throw new IllegalArgumentException("El cliente o la cuenta no existen");
        }

        Account account = existingAccount.get();

        if (transaction.getAmount().compareTo(account.getBalance()) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar la transacción");
        }

        transaction.setAccount(account);
        account.setBalance(account.getBalance().subtract(transaction.getAmount()));

        accountRepositoryPort.save(account);
        return transactionRepositoryPort.save(transaction);
    }
}
