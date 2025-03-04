package com.proyect.bankaccount.domain.model.transaction;

import com.proyect.bankaccount.domain.model.basic.AccountBasic;
import com.proyect.bankaccount.domain.model.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amount;

    private LocalDate transactionDate;

    private String description;

    private AccountBasic account;

}
