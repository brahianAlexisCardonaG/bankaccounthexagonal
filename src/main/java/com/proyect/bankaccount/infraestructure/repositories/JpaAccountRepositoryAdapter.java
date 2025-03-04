package com.proyect.bankaccount.infraestructure.repositories;

import com.proyect.bankaccount.domain.ports.out.AccountRepositoryPort;
import com.proyect.bankaccount.infraestructure.entities.AccountEntity;
import com.proyect.bankaccount.domain.model.account.Account;
import com.proyect.bankaccount.infraestructure.mapper.IAccountMapperEntityDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {

    private final JpaAccountRepository jpaAccountRepository;

    private final IAccountMapperEntityDomain accountMapper;

    @Autowired
    public JpaAccountRepositoryAdapter(JpaAccountRepository jpaAccountRepository
            , IAccountMapperEntityDomain accountMapper) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        Optional<AccountEntity> findAccount = jpaAccountRepository.findByAccountNumber(accountNumber);
        return findAccount.map(accountMapper::toAccount);
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountMapper.toAccountEntity(account);
        AccountEntity saveAccountEntity = jpaAccountRepository.save(accountEntity);
        return accountMapper.toAccount(saveAccountEntity);
    }

    @Override
    public Optional<Account> findById(Long id) {
        Optional<AccountEntity> findAccount = jpaAccountRepository.findById(id);
        return findAccount.map(accountMapper::toAccount);
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> findAllAccounts = jpaAccountRepository.findAll();

        findAllAccounts.forEach(accountEntity -> {
            accountEntity.getTransactions().forEach(transaction -> transaction.setAccount(null));
        });

        return findAllAccounts.stream()
                .map(accountMapper::toAccount)
                .toList();
    }

    @Override
    public Account updateBalance(Long id, BigDecimal balance) {
        Optional<AccountEntity> optionalAccount = jpaAccountRepository.findById(id);
        AccountEntity accountEntity = optionalAccount.get();
        accountEntity.setBalance(balance);
        AccountEntity updatedAccount = jpaAccountRepository.save(accountEntity);
        return accountMapper.toAccount(updatedAccount);
    }

}
