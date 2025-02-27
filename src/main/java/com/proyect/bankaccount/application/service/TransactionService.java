package com.proyect.bankaccount.application.service;

import com.proyect.bankaccount.domain.model.Transaction;
import com.proyect.bankaccount.domain.ports.in.transaction.CreateTransactionUseCase;

public class TransactionService implements CreateTransactionUseCase {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionService(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @Override
    public Transaction CreateTransaction(Transaction transaction, String identificationNumber, String accountNumber) {
        return createTransactionUseCase.CreateTransaction(transaction, identificationNumber, accountNumber);
    }
}
