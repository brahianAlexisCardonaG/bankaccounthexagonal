package com.proyect.bankaccount.application.mapper;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.infraestructure.controllers.account.response.AccountClientTransactionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAccountMapperResponseDomain {
    AccountClientTransactionResponse toAccountResponse(Account account);
    List<AccountClientTransactionResponse> toAccountResponseList(List<Account> accounts);
}
