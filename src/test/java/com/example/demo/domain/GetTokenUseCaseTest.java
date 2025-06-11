package com.example.demo.domain;

import com.example.demo.domain.api.usecase.GetTokenUseCase;
import com.example.demo.domain.spi.TokenServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GetTokenUseCaseTest {
    private TokenServicePort tokenServicePort;
    private GetTokenUseCase getTokenUseCase;

    @BeforeEach
    void setUp() {
        tokenServicePort = mock(TokenServicePort.class);
        getTokenUseCase = new GetTokenUseCase(tokenServicePort);
    }

    @Test
    void getToken_shouldReturnTokenFromService() {

        String expectedToken = "abc123";
        when(tokenServicePort.getToken()).thenReturn(expectedToken);


        String actualToken = getTokenUseCase.getToken();


        assertEquals(expectedToken, actualToken);
        verify(tokenServicePort).getToken();
    }
}
