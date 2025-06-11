package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Supplier;
import com.example.demo.domain.spi.ISupplierClientPort;

import java.util.List;

public class GetAllSupplierPort implements ISupplierPort {

    private final ISupplierClientPort supplierPort;

    public GetAllSupplierPort(ISupplierClientPort supplierPort) {
        this.supplierPort = supplierPort;
    }

    @Override
    public List<Supplier> getSuppliers() {
        return supplierPort.getSuppliers();
    }
}
