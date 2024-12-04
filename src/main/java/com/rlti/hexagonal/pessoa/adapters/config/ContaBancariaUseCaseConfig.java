package com.rlti.hexagonal.pessoa.adapters.config;

import com.rlti.hexagonal.pessoa.application.core.usecase.ContaBancariaUseCase;
import com.rlti.hexagonal.pessoa.application.port.output.ContaBancariaOutputPort;
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
