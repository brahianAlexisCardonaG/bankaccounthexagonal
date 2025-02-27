package com.proyect.bankaccount.domain.ports.out;

import com.proyect.bankaccount.domain.model.Transaction;

import java.util.List;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);

}
