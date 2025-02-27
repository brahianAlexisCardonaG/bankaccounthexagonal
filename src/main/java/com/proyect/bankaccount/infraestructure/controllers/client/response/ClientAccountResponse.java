package com.proyect.bankaccount.infraestructure.controllers.client.response;

import com.proyect.bankaccount.infraestructure.controllers.account.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String identificationNumber;

    private LocalDate createdAt;

    private List<AccountResponse> account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<AccountResponse> getAccount() {
        return account;
    }

    public void setAccount(List<AccountResponse> account) {
        this.account = account;
    }
}
