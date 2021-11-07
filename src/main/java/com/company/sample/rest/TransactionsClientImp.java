package com.company.sample.rest;

import com.company.sample.config.ExecutorConfig;
import com.company.sample.model.Transaction;
import com.company.sample.model.TransactionOperation;
import com.company.sample.model.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionsClientImp implements TransactionsClient {

    private final WebClient webClient;

    @Override
    public TransactionResponse executeTransaction(TransactionOperation trx) {
        return this.webClient.post().bodyValue(trx).retrieve().bodyToMono(TransactionResponse.class).block();
    }
}
