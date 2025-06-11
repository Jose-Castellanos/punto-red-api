package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.ApiResponse;
import com.example.demo.domain.api.usecase.ITokenPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final ITokenPort tokenPort;

    public TokenController(ITokenPort tokenPort) {
        this.tokenPort = tokenPort;
    }

    @GetMapping("/auth")
    public ResponseEntity<ApiResponse> getToken() {
        try {
            String token = tokenPort.getToken();
            return ResponseEntity.ok(new ApiResponse("Token obtenido exitosamente", token));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error interno del servidor", null));
        }
    }
}
