package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Transaction;

import java.util.List;

public interface ITransactionPort {
    List<Transaction> getAllTransactions(String idUser);

}
