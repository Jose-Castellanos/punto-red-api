package com.example.demo.configuration;

import com.example.demo.adapters.driven.jpa.adapter.TransactionAdapter;
import com.example.demo.adapters.driven.jpa.mapper.ITransactionMapper;
import com.example.demo.adapters.driven.repository.ITransactionRepository;
import com.example.demo.domain.api.usecase.*;
import com.example.demo.domain.spi.IRechargeClientPort;
import com.example.demo.domain.spi.ISupplierClientPort;
import com.example.demo.domain.spi.ITransactionPersistentePort;
import com.example.demo.domain.spi.TokenServicePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


    @Bean
    public GetTokenUseCase getTokenUseCase(TokenServicePort tokenServicePort) {
        return new GetTokenUseCase(tokenServicePort);
    }

    @Bean
    public GetAllSupplierPort getAllSupplierPort(ISupplierClientPort tokenServicePort) {
        return new GetAllSupplierPort(tokenServicePort);
    }

    @Bean
    public ITransactionPersistentePort transactionPersistentePort(ITransactionRepository transactionRepository, ITransactionMapper transactionMapper) {
        return new TransactionAdapter(transactionRepository, transactionMapper);
    }

    @Bean
    public SaveRechargeUseCase saveRechargeUseCase(IRechargeClientPort supplierClientPort, ITransactionPersistentePort transactionPersistentPort) {
        return new SaveRechargeUseCase(supplierClientPort, transactionPersistentPort);
    }

    @Bean
    public TransactionsUseCase transactionsUseCase(ITransactionPersistentePort iTransactionPersistentePort){
        return new TransactionsUseCase(iTransactionPersistentePort);
    }
}
