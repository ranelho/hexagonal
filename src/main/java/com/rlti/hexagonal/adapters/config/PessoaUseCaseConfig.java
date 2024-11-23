package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.application.core.usecase.PessoaUserCase;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaUseCaseConfig {

    @Bean
    public PessoaUserCase pessoaUserCase(PessoaOutputPort pessoaOutputPort) {
        return new PessoaUserCase(pessoaOutputPort);
    }
}

