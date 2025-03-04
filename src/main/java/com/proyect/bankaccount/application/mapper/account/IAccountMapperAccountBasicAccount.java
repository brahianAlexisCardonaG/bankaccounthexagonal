package com.proyect.bankaccount.application.mapper.account;

import com.proyect.bankaccount.domain.model.account.Account;
import com.proyect.bankaccount.domain.model.basic.AccountBasic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapperAccountBasicAccount {
    Account toAccount(AccountBasic accountBasic);
    AccountBasic toAccountBasic(Account account);
}
