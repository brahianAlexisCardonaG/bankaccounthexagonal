package com.proyect.bankaccount.domain.ports.in.account;

import com.proyect.bankaccount.domain.model.Account;

public interface CreateAccountUseCase {
    Account createAccount(Account account);
}
