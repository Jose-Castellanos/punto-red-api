package com.example.demo.adapters.driven.jpa.mapper;

import com.example.demo.adapters.driven.jpa.entity.TransactionEntity;
import com.example.demo.domain.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {
    TransactionEntity toEntity(Transaction transaction);

    Transaction toDto(TransactionEntity transactionEntity);

    List<Transaction> toDomain(List<TransactionEntity> transactionEntities);
}
