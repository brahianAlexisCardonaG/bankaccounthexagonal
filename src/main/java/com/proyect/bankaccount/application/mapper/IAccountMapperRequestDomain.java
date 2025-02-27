package com.proyect.bankaccount.application.mapper;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.infraestructure.controllers.account.request.AccountClientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapperRequestDomain {
    AccountClientRequest toAccountRequest(Account account);
    Account toAccount(AccountClientRequest AccountClientRequest);
}
