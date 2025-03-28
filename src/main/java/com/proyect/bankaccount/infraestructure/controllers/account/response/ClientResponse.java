package com.proyect.bankaccount.infraestructure.controllers.account.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientResponse {

    private String name;

    private String email;

    private String phone;

    private String identificationNumber;

    private LocalDate createdAt;

}
