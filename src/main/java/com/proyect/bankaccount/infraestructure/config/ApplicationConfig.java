package com.proyect.bankaccount.infraestructure.config;

import com.proyect.bankaccount.application.mapper.account.IAccountMapperAccountBasicAccount;
import com.proyect.bankaccount.application.mapper.client.IClientMapperClientBasicClient;
import com.proyect.bankaccount.application.service.AccountService;
import com.proyect.bankaccount.application.service.ClientService;
import com.proyect.bankaccount.application.service.MessageService;
import com.proyect.bankaccount.application.service.TransactionService;
import com.proyect.bankaccount.application.usecase.account.CreateAccountUseCaseImpl;
import com.proyect.bankaccount.application.usecase.account.GetAccountUseCaseImpl;
import com.proyect.bankaccount.application.usecase.client.CreateClientUseCaseImpl;
import com.proyect.bankaccount.application.usecase.client.GetClientUseCaseImpl;
import com.proyect.bankaccount.application.usecase.transaction.CreateTransactionUseCaseImpl;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.ClientRepositoryPort;
import com.proyect.bankaccount.domain.ports.out.TransactionRepositoryPort;
import com.proyect.bankaccount.infraestructure.repositories.JpaAccountRepositoryAdapter;
import com.proyect.bankaccount.infraestructure.repositories.JpaClientRepositoryAdapter;
import com.proyect.bankaccount.infraestructure.repositories.JpaTransactionRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ApplicationConfig {

    @Bean
    public AccountService accountService(AccountRepositoryPort accountRepositoryPort
                                         , ClientRepositoryPort clientRepositoryPort
                                         , MessageService messageService
                                         ,IClientMapperClientBasicClient clientMapperClientBasicClient
    ) {
        return new AccountService(
                new CreateAccountUseCaseImpl(accountRepositoryPort,clientRepositoryPort, messageService, clientMapperClientBasicClient),
                new GetAccountUseCaseImpl(accountRepositoryPort, messageService)
        );
    }

    @Bean
    public ClientService clientService(ClientRepositoryPort clientRepositoryPort
                                       , MessageService messageService
    ) {
        return new ClientService(
                new CreateClientUseCaseImpl(clientRepositoryPort, messageService),
                new GetClientUseCaseImpl(clientRepositoryPort, messageService),
                messageService);
    }

    @Bean
    public TransactionService transactionService(TransactionRepositoryPort transactionRepositoryPort
                                                 , AccountRepositoryPort accountRepositoryPort
                                                 , ClientRepositoryPort clientRepositoryPort
                                                 , MessageService messageService
                                                 , IAccountMapperAccountBasicAccount accountMapperAccountBasicAccount
    ) {
        return new TransactionService(
                new CreateTransactionUseCaseImpl(transactionRepositoryPort,
                        accountRepositoryPort,
                        clientRepositoryPort,
                        messageService,
                        accountMapperAccountBasicAccount
                )
        );
    }

    @Bean
    public AccountRepositoryPort accountRepositoryPort(JpaAccountRepositoryAdapter jpaAccountRepositoryAdapter){
        return jpaAccountRepositoryAdapter;
    }

    @Bean
    public ClientRepositoryPort clientRepositoryPort (JpaClientRepositoryAdapter jpaClientRepositoryAdapter){
        return jpaClientRepositoryAdapter;
    }

    @Bean
    public TransactionRepositoryPort transactionRepositoryPort (JpaTransactionRepositoryAdapter jpaTransactionRepositoryAdapter){
        return jpaTransactionRepositoryAdapter;
    }
}
