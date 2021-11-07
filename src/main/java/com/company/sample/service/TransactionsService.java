package com.company.sample.service;

import com.company.sample.model.Transaction;
import com.company.sample.model.TransactionOperation;
import com.company.sample.model.TransactionResponse;

import java.util.List;

public interface TransactionsService {

    List<Transaction> getTransactionsBySender(String senderId);

    List<Transaction> getAllTransactions();

    TransactionResponse executeTransaction(TransactionOperation trx);
}
