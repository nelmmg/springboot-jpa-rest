package com.company.sample.repo;

import com.company.sample.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionsRepo extends JpaRepository<Transaction, UUID> {

    List<Transaction> findTransactionBySender(String senderId);

}

