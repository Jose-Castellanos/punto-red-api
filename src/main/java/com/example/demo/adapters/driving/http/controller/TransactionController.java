package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.ApiResponse;

import com.example.demo.domain.api.usecase.ITokenPort;
import com.example.demo.domain.api.usecase.ITransactionPort;
import com.example.demo.domain.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final ITransactionPort iTransactionPort;

    public TransactionController(ITransactionPort iTransactionPort) {
        this.iTransactionPort = iTransactionPort;
    }


    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse> getAllTransactionsById(@AuthenticationPrincipal Jwt jwt) {
        try {
            List<Transaction> transactions = iTransactionPort.getAllTransactions(jwt.getSubject());
            return ResponseEntity.ok(new ApiResponse("Transacciones obtenidas exitosamente", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error interno del servidor", jwt.getSubject()));
        }
    }


}
