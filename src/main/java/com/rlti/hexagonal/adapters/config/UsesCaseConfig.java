package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.application.core.usecase.BuscaEnderecoUseCase;
import com.rlti.hexagonal.application.port.output.BuscaEnderecoOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsesCaseConfig {
    @Bean
    public BuscaEnderecoUseCase buscaEnderecoUseCase(BuscaEnderecoOutputPort buscaEnderecoOutputPort) {
        return new BuscaEnderecoUseCase(buscaEnderecoOutputPort);
    }
}

