package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.application.core.usecase.ContaBancariaUseCase;
import com.rlti.hexagonal.application.port.input.ContaBancariaInputPort;
import com.rlti.hexagonal.application.port.output.ContaBancariaOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContaBancariaUseCaseConfig {

    @Bean
    public ContaBancariaUseCase agenciaBancariaUseCase(ContaBancariaOutputPort contaBancariaOutputPort, ModelMapper modelMapper) {
        return new ContaBancariaUseCase(contaBancariaOutputPort, modelMapper);
    }
}
