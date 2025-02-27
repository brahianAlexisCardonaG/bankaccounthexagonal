package com.proyect.bankaccount.domain.ports.out;

import com.proyect.bankaccount.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {
    Optional<Account> findByAccountNumber(String accountNumber);
    Account save(Account account);
    Optional<Account> findById(Long id);
    List<Account> findAll();
}
