package com.proyect.bankaccount.infraestructure.mapper;

import com.proyect.bankaccount.domain.model.account.Account;
import com.proyect.bankaccount.infraestructure.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAccountMapperEntityDomain {

    AccountEntity toAccountEntity(Account account);

    Account toAccount(AccountEntity accountEntity);
}
