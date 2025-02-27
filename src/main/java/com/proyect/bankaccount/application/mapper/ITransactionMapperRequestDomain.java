package com.proyect.bankaccount.application.mapper;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.domain.model.Transaction;
import com.proyect.bankaccount.infraestructure.controllers.account.request.AccountClientRequest;
import com.proyect.bankaccount.infraestructure.controllers.transaction.request.TransactionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITransactionMapperRequestDomain {
    TransactionRequest toTransacctionRequest(Transaction transaction);
    Transaction toTransaction(TransactionRequest transactionRequest);
}
