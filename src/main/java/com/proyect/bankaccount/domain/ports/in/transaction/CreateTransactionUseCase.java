package com.proyect.bankaccount.domain.ports.in.transaction;

import com.proyect.bankaccount.domain.model.transaction.Transaction;

public interface CreateTransactionUseCase {
    Transaction CreateTransaction(Transaction transaction, String identificationNumber, String accountNumber);
}
