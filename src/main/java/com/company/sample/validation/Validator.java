package com.company.sample.validation;

import com.company.sample.exceptions.InvalidTransactionException;
import com.company.sample.model.TransactionOperation;

public class Validator {


    //TODO This can be achieved with custom Validator attached to the model
    public static void validateTransaction(TransactionOperation trx) {

        //Replace by Strings from Guava
        if(trx.getAmount() == null ||
            trx.getSender() == null ||
            trx.getReceiver() == null ||
            trx.getReceiver().isEmpty() ||
            trx.getSender().isEmpty()) {
            throw new InvalidTransactionException("All the fields are mandatory.");
        }
    }
}
