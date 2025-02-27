package com.proyect.bankaccount.application.usecase.account;

import com.proyect.bankaccount.domain.model.Account;
import com.proyect.bankaccount.domain.ports.in.account.GetAccountUseCase;
import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;

import java.util.List;
import java.util.Optional;

public class GetAccountUseCaseImpl implements GetAccountUseCase {

    private final AccountRepositoryPort  accountRepositoryPort;

    public GetAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepositoryPort.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepositoryPort.findById(id);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepositoryPort.findByAccountNumber(accountNumber);
    }
}
