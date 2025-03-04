package com.proyect.bankaccount.domain.model.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientBasic {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String identificationNumber;

    private LocalDate createdAt;
}
