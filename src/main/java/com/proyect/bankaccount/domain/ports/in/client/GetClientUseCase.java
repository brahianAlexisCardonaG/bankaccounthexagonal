package com.proyect.bankaccount.domain.ports.in.client;

import com.proyect.bankaccount.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface GetClientUseCase {
    List<Client> getAllClient();
    Optional<Client> getClientById(Long id);

    Optional<Client> findByIdentificationNumber(String identificationNumber);
}
