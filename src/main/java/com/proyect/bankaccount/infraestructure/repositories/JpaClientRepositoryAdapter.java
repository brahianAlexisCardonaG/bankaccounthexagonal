package com.proyect.bankaccount.infraestructure.repositories;

import com.proyect.bankaccount.domain.model.Client;
import com.proyect.bankaccount.domain.model.client.ClientAccount;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import com.proyect.bankaccount.infraestructure.entities.AccountEntity;
import com.proyect.bankaccount.infraestructure.entities.ClientEntity;
import com.proyect.bankaccount.infraestructure.entities.TransactionEntity;
import com.proyect.bankaccount.infraestructure.mapper.IClientMapperEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaClientRepositoryAdapter implements ClientRepositoryPort {

    private final JpaClientRepository jpaClientRepository;


    private final IClientMapperEntityDomain clientMapperEntityDomaintMapper;

    @Autowired
    public JpaClientRepositoryAdapter(JpaClientRepository jpaClientRepository, IClientMapperEntityDomain clientMapperEntityDomaintMapper){
        this.clientMapperEntityDomaintMapper = clientMapperEntityDomaintMapper;
        this.jpaClientRepository =jpaClientRepository;
    }

    @Override
    public Optional<Client> findById(Long id) {
        Optional<ClientEntity> findClient = jpaClientRepository.findById(id);
        return findClient.map(clientMapperEntityDomaintMapper::toClient);
    }

    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = clientMapperEntityDomaintMapper.toClientEntity(client);
        ClientEntity savedClient = jpaClientRepository.save(clientEntity);
        return clientMapperEntityDomaintMapper.toClient(savedClient);
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> findAllClient = jpaClientRepository.findAll();

        // Clonar la lista sin modificar la original
        List<ClientEntity> processedClients = findAllClient.stream()
                .map(clientEntity -> {
                    List<AccountEntity> accounts = clientEntity.getAccount().stream()
                            .map(account -> {
                                List<TransactionEntity> transactions = account.getTransactions().stream()
                                        .map(transaction -> new TransactionEntity(
                                                transaction.getId(),
                                                transaction.getTransactionType(),
                                                transaction.getAmount(),
                                                transaction.getTransactionDate(),
                                                transaction.getDescription(),
                                                null // <- Volver null la cuenta en cada transacción
                                        ))
                                        .toList();

                                AccountEntity accountCopy = new AccountEntity(
                                        account.getId(),
                                        account.getAccountNumber(),
                                        account.getAccountType(),
                                        account.getBalance(),
                                        account.getCreatedAt(),
                                        null, // <- Volver null al cliente
                                        transactions
                                );
                                return accountCopy;
                            })
                            .toList();

                    ClientEntity clientCopy = new ClientEntity(
                            clientEntity.getId(),
                            clientEntity.getName(),
                            clientEntity.getEmail(),
                            clientEntity.getPhone(),
                            clientEntity.getIdentificationNumber(),
                            clientEntity.getCreatedAt(),
                            accounts
                    );

                    return clientCopy;
                })
                .toList();

        List<ClientAccount> clientAccount = processedClients.stream()
                .map(clientMapperEntityDomaintMapper::toClientAccount)
                .toList();

        List<Client> client = clientAccount.stream()
                .map(clientMapperEntityDomaintMapper::clientAccountToClient)
                .toList();

        return client;
    }

    @Override
    public Optional<Client> findByIdentificationNumber(String identificationNumber) {
        Optional<ClientEntity> findClient = jpaClientRepository.findByIdentificationNumber(identificationNumber);

        return findClient.map(clientEntity -> {
            // 🔹 Rompe la referencia circular antes de mapear
            if (clientEntity.getAccount() != null) {
                clientEntity.getAccount().forEach(account -> account.setClient(null));
            }

            return clientMapperEntityDomaintMapper.toClient(clientEntity);
        });
    }
}
