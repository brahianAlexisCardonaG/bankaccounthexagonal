package com.proyect.bankaccount.infraestructure.controllers.account;

import com.proyect.bankaccount.application.mapper.account.IAccountMapperRequestDomain;
import com.proyect.bankaccount.application.mapper.account.IAccountMapperResponseDomain;
import com.proyect.bankaccount.application.service.AccountService;
import com.proyect.bankaccount.domain.model.account.Account;
import com.proyect.bankaccount.infraestructure.controllers.account.request.AccountClientRequest;
import com.proyect.bankaccount.infraestructure.controllers.account.response.AccountClientTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final IAccountMapperRequestDomain accountMapperRequestDomain;

    private final IAccountMapperResponseDomain accountMapperResponseDomain;

    @Autowired
    public AccountController(AccountService accountService
            , IAccountMapperRequestDomain accountMapperRequestDomain
            , IAccountMapperResponseDomain accountMapperResponseDomain
    ) {
        this.accountService = accountService;
        this.accountMapperRequestDomain = accountMapperRequestDomain;
        this.accountMapperResponseDomain = accountMapperResponseDomain;
    }

    @PostMapping(value = "/create-account", headers = "Accept=application/json")
    public ResponseEntity<String> save(@RequestBody AccountClientRequest accountClientRequest) {
        Account account = accountMapperRequestDomain.toAccount(accountClientRequest);
        accountService.createAccount(account);
        return new ResponseEntity<>("Cuenta creada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping(value = "/list", headers = "Accept=application/json")
    public ResponseEntity<List<AccountClientTransactionResponse>> getAll(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String accountNumber
    ) {
        List<AccountClientTransactionResponse> responseList;

        if (id != null) {
            Optional<Account> account = accountService.getAccountById(id);
            responseList = account
                    .map(a -> Collections.singletonList(accountMapperResponseDomain.toAccountResponse(a)))
                    .orElse(Collections.emptyList());
        } else if (accountNumber != null) {
            Optional<Account> accountListNumber = accountService.findByAccountNumber(accountNumber);
            responseList = accountListNumber
                    .map(a -> Collections.singletonList(accountMapperResponseDomain.toAccountResponse(a)))
                    .orElse(Collections.emptyList());
        }else {
            List<Account> accountListAll = accountService.getAllAccounts();
            responseList = accountMapperResponseDomain.toAccountResponseList(accountListAll);
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
