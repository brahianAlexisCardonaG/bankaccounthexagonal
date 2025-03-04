package com.proyect.bankaccount.application.usecase.transaction;

import com.proyect.bankaccount.application.mapper.account.IAccountMapperAccountBasicAccount;
import com.proyect.bankaccount.application.service.MessageService;
import com.proyect.bankaccount.domain.exception.GeneralNotFoundException;
import com.proyect.bankaccount.domain.model.account.Account;
import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.domain.model.transaction.Transaction;
import com.proyect.bankaccount.domain.ports.in.transaction.CreateTransactionUseCase;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.TransactionRepositoryPort;

import java.time.LocalDate;
import java.util.Optional;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    private final AccountRepositoryPort accountRepositoryPort;

    private final ClientRepositoryPort clientRepositoryPort;

    private final MessageService messageService;

    private final IAccountMapperAccountBasicAccount accountMapperAccountBasicAccount;


    public CreateTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort, AccountRepositoryPort accountRepositoryPort, ClientRepositoryPort clientRepositoryPort, MessageService messageService, IAccountMapperAccountBasicAccount accountMapperAccountBasicAccount) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.accountRepositoryPort = accountRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
        this.messageService = messageService;
        this.accountMapperAccountBasicAccount = accountMapperAccountBasicAccount;
    }

    @Override
    public Transaction CreateTransaction(Transaction transaction, String identificationNumber, String accountNumber) {

        Optional<Account> existingAccount = accountRepositoryPort.findByAccountNumber(accountNumber);
        Optional<Client> existingClient = clientRepositoryPort.findByIdentificationNumber(identificationNumber);
        if (!existingAccount.isPresent() || !existingClient.isPresent()) {
            throw new GeneralNotFoundException(messageService
                    .getMessage("error.account.code.client.code.not.exist", 0));
        }

        Account account = existingAccount.get();

        if (transaction.getAmount().compareTo(account.getBalance()) > 0) {
            throw new GeneralNotFoundException(messageService
                    .getMessage("error.account.insufficient.balance",0));
        }
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAccount(accountMapperAccountBasicAccount.toAccountBasic(account));
        account.setBalance(account.getBalance().subtract(transaction.getAmount()));

        accountRepositoryPort.updateBalance(account.getId(),account.getBalance());
        return transactionRepositoryPort.save(transaction);
    }
}
