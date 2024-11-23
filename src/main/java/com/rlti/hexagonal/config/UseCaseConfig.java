package com.rlti.hexagonal.config;

import com.rlti.hexagonal.application.core.usecase.PessoaUserCase;
import com.rlti.hexagonal.application.ports.in.PessoaInputPort;
import com.rlti.hexagonal.application.ports.out.PessoaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    private final PessoaOutputPort pessoaOutputPort;

    @Bean
    public PessoaInputPort insertPessoaInputPort() {
        return new PessoaUserCase(pessoaOutputPort);
    }
}
