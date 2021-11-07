package com.company.sample.controller;


import com.company.sample.model.Transaction;
import com.company.sample.model.TransactionOperation;
import com.company.sample.model.TransactionResponse;
import com.company.sample.service.TransactionsService;
import com.company.sample.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/api")
public class TransactionsController {

  private final TransactionsService service;

  @Autowired
  public TransactionsController(TransactionsService service) {
    this.service = service;
  }

  @GetMapping(value = "/transactions/{senderId}")
  @ResponseStatus(HttpStatus.OK)
  public List<Transaction> getTransactionsForSender(@PathVariable(value = "senderId") String senderId){

    return this.service.getTransactionsBySender(senderId);

  }

  @GetMapping(value = "/transactions")
  @ResponseStatus(HttpStatus.OK)
  public List<Transaction> getAllTransactions(){
    return this.service.getAllTransactions();
  }

  @PostMapping(value = "/transaction")
  @ResponseStatus(HttpStatus.OK)
  public TransactionResponse performTransaction(@RequestBody TransactionOperation trx) {

    Validator.validateTransaction(trx);
    return this.service.executeTransaction(trx);

  }

}
