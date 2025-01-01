package com.rlti.hexagonal.pessoa.config;

import com.rlti.hexagonal.pessoa.application.core.usecase.InstituicaoFinanceiraUseCase;
import com.rlti.hexagonal.pessoa.application.port.output.InstituicaoFinanceiraOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstituicaoFinanceiraUseCaseConfig {

    @Bean
    public InstituicaoFinanceiraUseCase instituicaoFinanceiraUseCase(InstituicaoFinanceiraOutputPort instituicaoFinanceiraOutputPort, ModelMapper modelMapper) {
        return new InstituicaoFinanceiraUseCase(instituicaoFinanceiraOutputPort, modelMapper);
    }

}

