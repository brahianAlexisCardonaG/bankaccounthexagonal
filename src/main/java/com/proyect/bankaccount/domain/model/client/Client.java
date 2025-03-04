package com.proyect.bankaccount.domain.model.client;

import com.proyect.bankaccount.domain.model.basic.AccountBasic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String identificationNumber;

    private LocalDate createdAt;

    private List<AccountBasic> account;

}
