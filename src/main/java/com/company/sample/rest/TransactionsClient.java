package com.company.sample.rest;

import com.company.sample.model.Transaction;
import com.company.sample.model.TransactionOperation;
import com.company.sample.model.TransactionResponse;

import java.util.UUID;

public interface TransactionsClient {

    TransactionResponse executeTransaction(TransactionOperation trx);

}
