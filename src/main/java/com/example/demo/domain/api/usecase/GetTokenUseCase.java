package com.example.demo.domain.api.usecase;


import com.example.demo.domain.spi.TokenServicePort;

public class GetTokenUseCase implements ITokenPort {

    private final TokenServicePort tokenServicePort;

    public GetTokenUseCase(TokenServicePort tokenServicePort) {
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public String getToken() {
        return tokenServicePort.getToken();
    }
}