package com.proyect.bankaccount.infraestructure.controllers.account.request;

import com.proyect.bankaccount.domain.model.enums.AccountType;
import com.proyect.bankaccount.infraestructure.controllers.client.request.ClientRequest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountClientRequest {

    @NotNull
    @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType accountType;

    @NotNull
    @PositiveOrZero(message = "El saldo no puede ser negativo")
    private BigDecimal balance;

    private ClientRequest client;

}
