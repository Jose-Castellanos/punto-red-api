package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.ApiResponse;
import com.example.demo.domain.api.usecase.IRechargePort;
import com.example.demo.domain.model.Recharge;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RechargeController {

    private final IRechargePort rechargePort;

    public RechargeController(IRechargePort rechargePort) {
        this.rechargePort = rechargePort;
    }


    @PostMapping("/recharge")
    public ResponseEntity<ApiResponse> saveTransaction(@RequestBody Recharge recharge,@AuthenticationPrincipal Jwt jwt) {
        try {
            rechargePort.saveTransaction(recharge, jwt.getSubject());
            return ResponseEntity.ok(new ApiResponse("Transacción guardada exitosamente", recharge));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error interno del servidor", null));
        }
    }
}
