package com.rlti.hexagonal.pessoa.config;

import com.rlti.hexagonal.pessoa.application.core.usecase.EnderecoUseCase;
import com.rlti.hexagonal.pessoa.application.port.output.BuscaEnderecoOutputPort;
import com.rlti.hexagonal.pessoa.application.port.output.EnderecoOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoUseCaseConfig {
    @Bean
    public EnderecoUseCase buscaEnderecoUseCase(BuscaEnderecoOutputPort buscaEnderecoOutputPort, EnderecoOutputPort enderecoOutputPort, ModelMapper modelMapper) {
        return new EnderecoUseCase(buscaEnderecoOutputPort, enderecoOutputPort, modelMapper);
    }
}

