package com.rlti.hexagonal.pessoa.config;

import com.rlti.hexagonal.pessoa.application.core.usecase.TelefoneUseCase;
import com.rlti.hexagonal.pessoa.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.pessoa.application.port.output.TelefoneOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelefoneUseCaseConfig {

    @Bean
    public TelefoneUseCase telefoneUseCase(PessoaOutputPort pessoaOutputPort, TelefoneOutputPort telefoneOutputPort) {
        return new TelefoneUseCase(pessoaOutputPort, telefoneOutputPort);
    }

}

