package com.example.demo.adapters.driven.client;

import com.example.demo.domain.model.Supplier;
import com.example.demo.domain.spi.ISupplierClientPort;

import java.util.List;


import com.example.demo.domain.model.Supplier;
import com.example.demo.domain.spi.ISupplierClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.List;

@Component
public class SupplierServiceAdapter implements ISupplierClientPort {

    private final WebClient webClient;
    private final TokenServiceAdapter tokenServiceAdapter;

    public SupplierServiceAdapter(WebClient.Builder webClientBuilder, TokenServiceAdapter tokenServiceAdapter,@Value("${recharge.service.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.tokenServiceAdapter = tokenServiceAdapter;
    }

    @Override
    public List<Supplier> getSuppliers() {
        try {
            var token =tokenServiceAdapter.getToken();

            return webClient.get()
                    .uri("/getSuppliers")
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(Supplier[].class)
                    .map(Arrays::asList)
                    .block(); // Bloquea para simplificar, pero considera usar programación reactiva.
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error al obtener los proveedores: " + e.getMessage(), e);
        }
    }
}