package com.proyect.bankaccount.infraestructure.repositories;

import com.proyect.bankaccount.domain.model.transaction.Transaction;
import com.proyect.bankaccount.domain.ports.out.TransactionRepositoryPort;
import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import com.proyect.bankaccount.infraestructure.mapper.ITransactionMapperEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
