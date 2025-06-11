package com.example.demo.adapters.driving.http.controller;


import com.example.demo.domain.api.usecase.ISupplierPort;
import com.example.demo.domain.model.Supplier;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplierController {

    private final ISupplierPort supplierPort;

    public SupplierController(ISupplierPort supplierPort) {
        this.supplierPort = supplierPort;
    }

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.getId());
        return supplierPort.getSuppliers();
    }
}