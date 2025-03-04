package com.proyect.bankaccount.application.usecase.account;

import com.proyect.bankaccount.application.service.MessageService;
import com.proyect.bankaccount.domain.exception.GeneralNotFoundException;
import com.proyect.bankaccount.domain.ports.in.account.GetAccountUseCase;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.domain.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GetAccountUseCaseImpl implements GetAccountUseCase {

    private final AccountRepositoryPort  accountRepositoryPort;

    private MessageService messageService;

    public GetAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort
                                 ,MessageService messageService
    ) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.messageService = messageService;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = accountRepositoryPort.findAll();
        if (accountList.isEmpty()){
            throw new GeneralNotFoundException(messageService.getMessage("error.account.code.not.found"));
        }
        return accountList;
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepositoryPort.findById(id);
        if (!accountOptional.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.account.not.found.id", id));
        }
        return accountOptional;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Optional<Account> accountOptional = accountRepositoryPort.findByAccountNumber(accountNumber);
        if (!accountOptional.isPresent()){
            throw new GeneralNotFoundException(messageService.getMessage("error.account.code.not.exist", accountNumber));
        }
        return accountOptional;
    }
}
