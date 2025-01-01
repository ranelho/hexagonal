package com.rlti.hexagonal.pessoa.config;

import com.rlti.hexagonal.pessoa.application.port.input.ProfissaoUseCase;
import com.rlti.hexagonal.pessoa.application.port.output.ProfissaoOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfissaoUseCaseConfig {

    @Bean
    public ProfissaoUseCase profissaoUseCase(ProfissaoOutputPort outputPort) {
        return new ProfissaoUseCase(outputPort);
    }
}
