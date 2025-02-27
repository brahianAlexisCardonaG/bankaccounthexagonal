package com.proyect.bankaccount.domain.ports.in.account;

import com.proyect.bankaccount.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface GetAccountUseCase {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long id);

    Optional<Account> findByAccountNumber(String accountNumber);
}
