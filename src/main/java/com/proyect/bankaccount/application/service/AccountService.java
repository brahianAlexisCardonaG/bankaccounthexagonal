package com.proyect.bankaccount.application.service;

import com.proyect.bankaccount.domain.ports.in.account.CreateAccountUseCase;
import com.proyect.bankaccount.domain.ports.in.account.GetAccountUseCase;
import com.proyect.bankaccount.domain.model.account.Account;
import java.util.List;
import java.util.Optional;

public class AccountService implements CreateAccountUseCase, GetAccountUseCase {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;


    public AccountService(CreateAccountUseCase createAccountUseCase
            , GetAccountUseCase getAccountUseCase
    ) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
    }

    @Override
    public Account createAccount(Account account) {
        return createAccountUseCase.createAccount(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return getAccountUseCase.getAllAccounts();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return getAccountUseCase.getAccountById(id);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return getAccountUseCase.findByAccountNumber(accountNumber);
    }
}
