package com.proyect.bankaccount.domain.ports.out;

import com.proyect.bankaccount.domain.model.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepositoryPort {
    Optional<Client> findById(Long id);
    Client save(Client client);

    List<Client> findAll();

    Optional<Client> findByIdentificationNumber(String identificationNumber);

    Optional<Client> findByEmail(String email);
}
