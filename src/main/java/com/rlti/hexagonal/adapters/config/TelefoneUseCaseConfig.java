package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.application.core.usecase.TelefoneUseCase;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.application.port.output.TelefoneOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelefoneUseCaseConfig {

    @Bean
    public TelefoneUseCase telefoneUseCase(PessoaOutputPort pessoaOutputPort, TelefoneOutputPort telefoneOutputPort) {
        return new TelefoneUseCase(pessoaOutputPort, telefoneOutputPort);
    }

}

