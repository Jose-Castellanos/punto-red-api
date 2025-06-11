package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.spi.ITransactionPersistentePort;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionsUseCase implements ITransactionPort{

    private final ITransactionPersistentePort iTransactionPersistentePort;

    public TransactionsUseCase(ITransactionPersistentePort iTransactionPersistentePort) {
        this.iTransactionPersistentePort = iTransactionPersistentePort;
    }

    @Override
    public List<Transaction> getAllTransactions(String idUser) {
        return iTransactionPersistentePort.getAllTransactions()
                .stream()
                .filter(transaction -> transaction.getIdUser() != null && transaction.getIdUser().equals(idUser))
                .collect(Collectors.toList());
    }


}
