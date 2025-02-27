package com.proyect.bankaccount.infraestructure.mapper;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.infraestructure.entities.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAccountMapperEntityDomain {
    @Mapping(target = "client", source = "client")
    AccountEntity toAccountEntity(Account account);

    @Mapping(target = "client.account", ignore = true)
    Account toAccount(AccountEntity accountEntity);
}
