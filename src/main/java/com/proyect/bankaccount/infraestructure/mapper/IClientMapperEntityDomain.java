package com.proyect.bankaccount.infraestructure.mapper;

import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.infraestructure.entities.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapperEntityDomain {
    ClientEntity toClientEntity(Client client);
    Client toClient(ClientEntity clientEntity);
}
