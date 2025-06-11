package com.example.demo.domain.spi;

import com.example.demo.domain.model.Transaction;

import java.util.List;

public interface ITransactionPersistentePort {

    void setTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();


}
