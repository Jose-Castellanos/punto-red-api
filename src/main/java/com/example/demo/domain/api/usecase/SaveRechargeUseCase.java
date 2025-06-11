package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Recharge;
import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.spi.IRechargeClientPort;
import com.example.demo.domain.spi.ITransactionPersistentePort;

public class SaveRechargeUseCase implements IRechargePort {

    private final IRechargeClientPort rechargeClientPort;
    private final ITransactionPersistentePort supplierRechargePersistentePort;

    public SaveRechargeUseCase(
                               IRechargeClientPort rechargeClientPort,
                               ITransactionPersistentePort supplierRechargePersistentePort) {
        this.rechargeClientPort = rechargeClientPort;
        this.supplierRechargePersistentePort = supplierRechargePersistentePort;
    }

    @Override
    public void saveTransaction(Recharge recharge, String user) {
        validateRecharge(recharge);
        String supplierName = recharge.getSupplierName();
        Transaction transaction = rechargeClientPort.saveRecharge(recharge);
        transaction.setNameSupplier(supplierName);
        transaction.setIdUser(user);
        transaction.setIdSupplier(recharge.getSupplierId());
        supplierRechargePersistentePort.setTransaction(transaction);
    }

    private void validateRecharge(Recharge recharge) {
        if (recharge.getValue() < 1000 || recharge.getValue() > 100000) {
            throw new IllegalArgumentException("El valor de la transacción debe estar entre 1,000 y 100,000.");
        }
        if (!recharge.getCellPhone().matches("^3\\d{9}$")) {
            throw new IllegalArgumentException("El número de teléfono debe iniciar con '3', tener 10 dígitos y ser numérico.");
        }
    }
}
