package com.company.sample.service;

import com.company.sample.model.Transaction;
import com.company.sample.model.TransactionOperation;
import com.company.sample.model.TransactionResponse;
import com.company.sample.repo.TransactionsRepo;
import com.company.sample.rest.TransactionsClient;
import com.ibm.icu.impl.Assert;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TransactionsServiceTests {

    @Mock
    private TransactionsClient client;

    @Mock
    private TransactionsRepo repo;

    private TransactionsService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.service = new TransactionsServiceImp(client, repo);
    }


    // List<Transaction> getTransactionsBySender(String senderId);
    // List<Transaction> getAllTransactions();
    // UUID executeTransaction(TransactionOperation trx);

    @Test
    public void getTransactionsBySender_success() {

        Transaction a = new Transaction(UUID.randomUUID(), "John", "Bob", 10.2);
        Transaction b = new Transaction(UUID.randomUUID(), "John", "Matheus", 3.0);
        Transaction c = new Transaction(UUID.randomUUID(), "John", "Anna", 20.3);

        List<Transaction> trxs = Arrays.asList(a, b, c);
        Mockito.when(repo.findTransactionBySender(Mockito.anyString())).thenReturn(trxs);

        List<Transaction> result = this.service.getTransactionsBySender("John");

        Assertions.assertEquals(trxs, result);
    }

    @Test
    public void getAllTransactions_success() {

        Transaction a = new Transaction(UUID.randomUUID(), "John", "Bob", 10.2);
        Transaction b = new Transaction(UUID.randomUUID(), "John", "Matheus", 3.0);
        Transaction c = new Transaction(UUID.randomUUID(), "John", "Anna", 20.3);
        Transaction d = new Transaction(UUID.randomUUID(), "John", "Anna", 20.3);

        List<Transaction> trxs = Arrays.asList(a, b, c, d);
        Mockito.when(repo.findAll()).thenReturn(trxs);

        List<Transaction> result = this.service.getAllTransactions();

        Assertions.assertEquals(trxs, result);


    }

    @Test
    public void executeTransaction_success() {

        TransactionOperation trxOp = new TransactionOperation("John", "Maria", 13.1);
        UUID mockedResult = UUID.randomUUID();

        TransactionResponse mockedResponse = new TransactionResponse(mockedResult);

        Mockito.when(repo.save(Mockito.any())).thenReturn(null);
        Mockito.when(client.executeTransaction(trxOp)).thenReturn(mockedResponse);

        TransactionResponse result = this.service.executeTransaction(trxOp);

        Assertions.assertEquals(mockedResult, result.getId());
    }
}
