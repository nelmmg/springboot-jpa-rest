package com.company.sample.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Transaction {

    @Id
    private UUID id;
    private String sender;
    private String receiver;
    private Double amount;

    public Transaction(UUID trxId, TransactionOperation trx) {
        this.id = trxId;
        this.sender = trx.getSender();
        this.receiver = trx.getReceiver();
        this.amount = trx.getAmount();
    }
}
