package com.proyect.bankaccount.application.mapper;

import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.infraestructure.controllers.client.response.ClientAccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IClientMapperResponseDomain {
    ClientAccountResponse toClientAccountResponse(Client client);
    List<ClientAccountResponse> toClientResponseList(List<Client> clients);
}
