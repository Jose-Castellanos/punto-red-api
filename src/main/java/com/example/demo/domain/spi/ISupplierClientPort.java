package com.example.demo.domain.spi;

import com.example.demo.domain.model.Supplier;

import java.util.List;

public interface ISupplierClientPort {

    List<Supplier> getSuppliers();
}
