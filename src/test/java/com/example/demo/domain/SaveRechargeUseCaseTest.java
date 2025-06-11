package com.example.demo.domain;

import com.example.demo.domain.api.usecase.SaveRechargeUseCase;
import com.example.demo.domain.model.Recharge;
import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.spi.IRechargeClientPort;
import com.example.demo.domain.spi.ITransactionPersistentePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveRechargeUseCaseTest {
    private IRechargeClientPort rechargeClientPort;
    private ITransactionPersistentePort transactionPersistentePort;
    private SaveRechargeUseCase saveRechargeUseCase;

    @BeforeEach
    void setUp() {
        rechargeClientPort = mock(IRechargeClientPort.class);
        transactionPersistentePort = mock(ITransactionPersistentePort.class);
        saveRechargeUseCase = new SaveRechargeUseCase(rechargeClientPort, transactionPersistentePort);
    }

    @Test
    void saveTransaction_validRecharge_shouldSaveTransaction() {

        Recharge recharge = buildRecharge(5000, "3001234567", "Supplier A", "SUP-001");
        String user = "user123";
        Transaction transaction = new Transaction();
        when(rechargeClientPort.saveRecharge(recharge)).thenReturn(transaction);


        saveRechargeUseCase.saveTransaction(recharge, user);

        
        assertEquals("Supplier A", transaction.getNameSupplier());
        assertEquals("user123", transaction.getIdUser());
        assertEquals("SUP-001", transaction.getIdSupplier());
        verify(transactionPersistentePort).setTransaction(transaction);
    }

    @Test
    void saveTransaction_rechargeBelowMinimum_shouldThrowException() {
        Recharge recharge = buildRecharge(999, "3001234567", "Supplier A", "SUP-001");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            saveRechargeUseCase.saveTransaction(recharge, "user123");
        });

        assertEquals("El valor de la transacción debe estar entre 1,000 y 100,000.", exception.getMessage());
        verifyNoInteractions(rechargeClientPort);
        verifyNoInteractions(transactionPersistentePort);
    }

    @Test
    void saveTransaction_rechargeAboveMaximum_shouldThrowException() {
        Recharge recharge = buildRecharge(100001, "3001234567", "Supplier A", "SUP-001");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            saveRechargeUseCase.saveTransaction(recharge, "user123");
        });

        assertEquals("El valor de la transacción debe estar entre 1,000 y 100,000.", exception.getMessage());
        verifyNoInteractions(rechargeClientPort);
        verifyNoInteractions(transactionPersistentePort);
    }

    @Test
    void saveTransaction_invalidPhoneNumber_shouldThrowException() {
        Recharge recharge = buildRecharge(5000, "2123456789", "Supplier A", "SUP-001");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            saveRechargeUseCase.saveTransaction(recharge, "user123");
        });

        assertEquals("El número de teléfono debe iniciar con '3', tener 10 dígitos y ser numérico.", exception.getMessage());
        verifyNoInteractions(rechargeClientPort);
        verifyNoInteractions(transactionPersistentePort);
    }

    private Recharge buildRecharge(int value, String cellPhone, String supplierName, String supplierId) {
        return new Recharge(value,supplierId, cellPhone, supplierName);
    }
}
