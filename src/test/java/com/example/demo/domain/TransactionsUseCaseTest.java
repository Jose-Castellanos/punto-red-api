package com.example.demo.domain;

import com.example.demo.domain.api.usecase.TransactionsUseCase;
import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.spi.ITransactionPersistentePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TransactionsUseCaseTest {
    private ITransactionPersistentePort iTransactionPersistentePort;
    private TransactionsUseCase transactionsUseCase;

    @BeforeEach
    void setUp() {
        iTransactionPersistentePort = mock(ITransactionPersistentePort.class);
        transactionsUseCase = new TransactionsUseCase(iTransactionPersistentePort);
    }

    @Test
    void getAllTransactions_shouldReturnOnlyTransactionsForGivenUser() {

        String userId = "user123";

        Transaction tx1 = buildTransaction("user123");
        Transaction tx2 = buildTransaction("user456");
        Transaction tx3 = buildTransaction("user123");

        List<Transaction> allTransactions = Arrays.asList(tx1, tx2, tx3);
        when(iTransactionPersistentePort.getAllTransactions()).thenReturn(allTransactions);


        List<Transaction> result = transactionsUseCase.getAllTransactions(userId);


        assertEquals(2, result.size());
        assertTrue(result.contains(tx1));
        assertTrue(result.contains(tx3));
        assertFalse(result.contains(tx2));
    }
    @Test
    void getAllTransactions_shouldReturnEmptyListWhenUserIdIsNull() {
        // Arrange
        Transaction tx1 = buildTransaction("user123");
        Transaction tx2 = buildTransaction("user456");
        Transaction tx3 = buildTransaction(null);

        List<Transaction> allTransactions = Arrays.asList(tx1, tx2, tx3);
        when(iTransactionPersistentePort.getAllTransactions()).thenReturn(allTransactions);

        // Act
        List<Transaction> result = transactionsUseCase.getAllTransactions(null);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    private Transaction buildTransaction(String userId) {
        Transaction tx = new Transaction();
        tx.setIdUser(userId);
        return tx;
    }
}
