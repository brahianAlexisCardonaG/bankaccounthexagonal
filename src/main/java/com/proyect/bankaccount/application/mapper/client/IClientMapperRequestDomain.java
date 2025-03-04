package com.proyect.bankaccount.application.mapper.client;

import com.proyect.bankaccount.domain.model.client.Client;
import com.proyect.bankaccount.infraestructure.controllers.client.request.ClientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapperRequestDomain {
    ClientRequest toClientRequest(Client client);
    Client toClient(ClientRequest clientRequest);
}
