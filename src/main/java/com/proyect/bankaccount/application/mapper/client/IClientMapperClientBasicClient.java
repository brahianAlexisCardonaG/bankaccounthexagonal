package com.proyect.bankaccount.application.mapper.client;

import com.proyect.bankaccount.domain.model.basic.ClientBasic;
import com.proyect.bankaccount.domain.model.client.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapperClientBasicClient {
    Client toClient(ClientBasic clientBasic);

    ClientBasic toClientBasic(Client client);
}
