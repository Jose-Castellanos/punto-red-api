package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Supplier;

import java.util.List;

public interface ISupplierPort {


    List<Supplier> getSuppliers();
}
