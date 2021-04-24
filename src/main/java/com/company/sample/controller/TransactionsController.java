package com.company.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class TransactionsController {

  @Autowired
  public TransactionsController() {
  }

  // TODO - Review the inputs / outputs

  @GetMapping(value = "/transactions/{senderId}")
  @ResponseStatus(HttpStatus.OK)
  public  void getTransactionsForSender(){
    throw new RuntimeException("Pending Implementation");
  }

  @GetMapping(value = "/transactions")
  @ResponseStatus(HttpStatus.OK)
  public void getAllTransactions(){
    throw new RuntimeException("Pending Implementation");
  }

  @PostMapping(value = "/transaction")
  @ResponseStatus(HttpStatus.OK)
  public void performTransaction() {
    throw new RuntimeException("Pending Implementation");
  }

}
