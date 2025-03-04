package com.proyect.bankaccount.domain.model.account;

import com.proyect.bankaccount.domain.model.basic.ClientBasic;
import com.proyect.bankaccount.domain.model.basic.TransactionBasic;
import com.proyect.bankaccount.domain.model.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private BigDecimal balance;

    private LocalDate createdAt;

    private ClientBasic client;

    private List<TransactionBasic> transactions;

}
