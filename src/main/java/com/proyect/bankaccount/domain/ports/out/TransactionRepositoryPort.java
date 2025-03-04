package com.proyect.bankaccount.domain.ports.out;

import com.proyect.bankaccount.domain.model.transaction.Transaction;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);

}
