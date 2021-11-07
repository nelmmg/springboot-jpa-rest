package com.company.sample.service;

import com.company.sample.model.Transaction;
import com.company.sample.model.TransactionOperation;
import com.company.sample.model.TransactionResponse;
import com.company.sample.repo.TransactionsRepo;
import com.company.sample.rest.TransactionsClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionsServiceImp implements TransactionsService {

    private TransactionsClient client;
    private TransactionsRepo repo;

    @Override
    public List<Transaction> getTransactionsBySender(String senderId) {

        //TODO Validate input?
        return this.repo.findTransactionBySender(senderId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        //TODO Validate input?
        return this.repo.findAll();
    }

    @Override
    public TransactionResponse executeTransaction(TransactionOperation trx) {

        //TODO Validate input?
        TransactionResponse trxId = this.client.executeTransaction(trx);
        this.repo.save(new Transaction(trxId.getId(), trx ));

        return trxId;
    }
}
