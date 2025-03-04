package com.proyect.bankaccount.infraestructure.controllers.transaction;

import com.proyect.bankaccount.application.mapper.transaction.ITransactionMapperRequestDomain;
import com.proyect.bankaccount.application.service.TransactionService;
import com.proyect.bankaccount.domain.model.transaction.Transaction;
import com.proyect.bankaccount.infraestructure.controllers.transaction.request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    private final ITransactionMapperRequestDomain transactionMapperRequestDomain;

    @Autowired
    public TransactionController(TransactionService transactionService, ITransactionMapperRequestDomain transactionMapperRequestDomain) {
        this.transactionService = transactionService;
        this.transactionMapperRequestDomain = transactionMapperRequestDomain;
    }

    @PostMapping( value="/create-transaction", headers = "Accept=application/json")
    public ResponseEntity<String> save(@RequestBody TransactionRequest transactionRequest
                                       ,@RequestParam String identificationNumber
                                        ,@RequestParam String accountNumber
                                       ) {
        Transaction transaction = transactionMapperRequestDomain.toTransaction(transactionRequest);
        transactionService.CreateTransaction(transaction, identificationNumber, accountNumber);
        return new ResponseEntity<>("Transacción exitosa", HttpStatus.CREATED);
    }
}
