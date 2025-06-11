package com.example.demo.adapters.driven.jpa.adapter;

import com.example.demo.adapters.driven.jpa.mapper.ITransactionMapper;
import com.example.demo.adapters.driven.repository.ITransactionRepository;
import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.spi.ITransactionPersistentePort;
import lombok.RequiredArgsConstructor;

import java.util.List;


public class TransactionAdapter implements ITransactionPersistentePort {


    private final ITransactionRepository transactionRepository;

    private final ITransactionMapper transactionMapper;

    public TransactionAdapter(ITransactionRepository transactionRepository, ITransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public void setTransaction(Transaction transaction) {
        if (transaction != null) {
            transactionRepository.save(transactionMapper.toEntity(transaction));
        } else {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

    }
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionMapper.toDomain(transactionRepository.findAll());
    }


}
