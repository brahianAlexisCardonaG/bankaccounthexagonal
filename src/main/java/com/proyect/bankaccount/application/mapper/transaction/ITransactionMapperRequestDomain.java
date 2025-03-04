package com.proyect.bankaccount.application.mapper.transaction;

import com.proyect.bankaccount.domain.model.transaction.Transaction;
import com.proyect.bankaccount.infraestructure.controllers.transaction.request.TransactionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITransactionMapperRequestDomain {
    TransactionRequest toTransacctionRequest(Transaction transaction);
    Transaction toTransaction(TransactionRequest transactionRequest);
}
