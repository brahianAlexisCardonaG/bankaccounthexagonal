package com.proyect.bankaccount.infraestructure.mapper;

import com.proyect.bankaccount.domain.model.Transaction;
import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITransactionMapperEntityDomain {
    TransactionEntity toTransctionEntity(Transaction transaction);

    @Mapping(target = "account.client", ignore = true)
    Transaction toTransaction(TransactionEntity transactionEntity);
}
