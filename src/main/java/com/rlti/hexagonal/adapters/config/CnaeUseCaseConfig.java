package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.application.core.usecase.CnaeUseCase;
import com.rlti.hexagonal.application.port.output.CnaeOutputPort;
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
