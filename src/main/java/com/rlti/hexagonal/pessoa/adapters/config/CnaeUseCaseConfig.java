package com.rlti.hexagonal.pessoa.adapters.config;

import com.rlti.hexagonal.pessoa.application.core.usecase.CnaeUseCase;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CnaeUseCaseConfig {

    @Bean
    public CnaeUseCase cnaeUseCase(CnaeOutputPort cnaePort, ModelMapper modelMapper) {
        return new CnaeUseCase(cnaePort, modelMapper);
    }
}
