package com.proyect.bankaccount.infraestructure.controllers.client.response;

import com.proyect.bankaccount.infraestructure.controllers.account.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientAccountResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String identificationNumber;

    private LocalDate createdAt;

    private List<AccountResponse> account;

}
