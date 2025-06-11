package com.example.demo.adapters.driven.client;

import com.example.demo.domain.spi.TokenServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class TokenServiceAdapter implements TokenServicePort {

    private final WebClient webClient;
    private final String apiKey;
    private final String username;
    private final String password;


    public TokenServiceAdapter(WebClient.Builder webClientBuilder,@Value("${recharge.service.base-url}") String baseUrl,@Value("${auth.service.api-key}") String apiKey,
                               @Value("${auth.service.username}") String username,
                               @Value("${auth.service.password}") String password) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getToken() {
        try {
            return webClient.post()
                    .uri("/auth")
                    .header("x-api-key", apiKey)
                    .bodyValue(createAuthRequest())
                    .retrieve()
                    .bodyToMono(AuthResponse.class)
                    .map(AuthResponse::getToken)
                    .block();

        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error al obtener el token: " + e.getMessage(), e);
        }
    }

    private AuthRequest createAuthRequest() {
        return new AuthRequest(username, password);
    }

    private static class AuthRequest {
        private final String user;
        private final String password;

        public AuthRequest(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }
    }

    private static class AuthResponse {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}