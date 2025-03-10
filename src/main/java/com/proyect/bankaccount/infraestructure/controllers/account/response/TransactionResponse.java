package com.proyect.bankaccount.infraestructure.controllers.account.response;

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
public class TransactionResponse {

    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amount;

    private LocalDate transactionDate;

    private String description;
}
