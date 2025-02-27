package com.proyect.bankaccount.infraestructure.repositories;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.domain.model.Transaction;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.TransactionRepositoryPort;
import com.proyect.bankaccount.infraestructure.entities.AccountEntity;
import com.proyect.bankaccount.infraestructure.entities.ClientEntity;
import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import com.proyect.bankaccount.infraestructure.mapper.IAccountMapperEntityDomain;
import com.proyect.bankaccount.infraestructure.mapper.ITransactionMapperEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaTransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository jpaAccountRepository;

    private final ITransactionMapperEntityDomain transactionMapperEntityDomain;

    @Autowired
    public JpaTransactionRepositoryAdapter(JpaTransactionRepository jpaAccountRepository, ITransactionMapperEntityDomain transactionMapperEntityDomain) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.transactionMapperEntityDomain = transactionMapperEntityDomain;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapperEntityDomain.toTransctionEntity(transaction);
        TransactionEntity savedTransaction = jpaAccountRepository.save(transactionEntity);
        return transactionMapperEntityDomain.toTransaction(savedTransaction);
    }
}
