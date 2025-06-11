package com.example.demo.adapters.driven.repository;

import com.example.demo.adapters.driven.jpa.entity.TransactionEntity;
import com.example.demo.adapters.driving.http.dto.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {
    TransactionEntity save(Transaction transaction);
    List<TransactionEntity> findAll();
}
