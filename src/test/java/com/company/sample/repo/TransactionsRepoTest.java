package com.company.sample.repo;

import com.company.sample.model.Transaction;
import com.ibm.icu.impl.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@DataJpaTest
public class TransactionsRepoTest {

    @Autowired
    TransactionsRepo repo;


    @Test
    public void shouldFilterBySender() {

        Transaction a = new Transaction(UUID.randomUUID(), "John", "Bob", 10.2);
        Transaction b = new Transaction(UUID.randomUUID(), "Manuel", "Matheus", 3.0);
        Transaction c = new Transaction(UUID.randomUUID(), "John", "Anna", 20.3);
        Transaction d = new Transaction(UUID.randomUUID(), "Bob", "John", 20.3);

        List<Transaction> johnsTrx = Arrays.asList(a, c);

        repo.save(a);
        repo.save(b);
        repo.save(c);
        repo.save(d);

        Assertions.assertEquals(4, repo.findAll().size());

        List<Transaction> result = repo.findTransactionBySender("John");
        Assertions.assertEquals(johnsTrx, result);
    }

}
