package com.proyect.bankaccount.infraestructure.mapper;

import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.model.client.ClientAccount;
import com.proyect.bankaccount.infraestructure.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IClientMapperEntityDomain {
    ClientEntity toClientEntity(Client client);
    @Mapping(target = "account.client", ignore = true)
    Client toClient(ClientEntity clientEntity);

    ClientAccount toClientAccount(ClientEntity clientEntity);
    Client clientAccountToClient(ClientAccount Client);
}
