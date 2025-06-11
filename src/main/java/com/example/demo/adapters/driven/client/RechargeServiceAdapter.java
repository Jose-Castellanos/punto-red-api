package com.example.demo.adapters.driven.client;

import com.example.demo.adapters.driven.client.mappers.RechargeClientMapper;
import com.example.demo.adapters.driven.client.model.RechargeClient;
import com.example.demo.domain.model.Recharge;
import com.example.demo.domain.model.Transaction;
import com.example.demo.domain.spi.IRechargeClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class RechargeServiceAdapter implements IRechargeClientPort {

    private final WebClient webClient;
    private final TokenServiceAdapter tokenServiceAdapter;
    private final RechargeClientMapper rechargeClientMapper;

    public RechargeServiceAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${recharge.service.base-url}") String baseUrl, TokenServiceAdapter tokenServiceAdapter, RechargeClientMapper rechargeClientMapper
    ) {
        this.tokenServiceAdapter = tokenServiceAdapter;
        this.rechargeClientMapper = rechargeClientMapper;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }
    @Override
    public Transaction saveRecharge(Recharge recharge) {

        RechargeClient rechargeClient= rechargeClientMapper.toClient(recharge);
        try {
            var token = tokenServiceAdapter.getToken();

            return webClient.post()
                    .uri("/buy")
                    .bodyValue(rechargeClient)
                    .header("Authorization", token)
                    .retrieve()
                    .bodyToMono(Transaction.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error al realizar la recarga: " + e.getMessage(), e);
        }
    }
}