package com.company.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionOperation {

    //TODO Add Validations
    private String sender;
    private String receiver;
    private Double amount;

}
