package com.proyect.bankaccount.infraestructure.mapper;

import com.proyect.bankaccount.domain.model.transaction.Transaction;
import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITransactionMapperEntityDomain {
    TransactionEntity toTransctionEntity(Transaction transaction);

    Transaction toTransaction(TransactionEntity transactionEntity);
}
