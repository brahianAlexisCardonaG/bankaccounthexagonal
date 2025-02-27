package com.proyect.bankaccount.domain.ports.in.client;

import com.proyect.bankaccount.domain.model.Client;

public interface CreateClientUseCase {
    Client createClient(Client client);
}
