package com.rlti.hexagonal.config;

import com.rlti.hexagonal.application.core.usecase.InsertPessoaUserCase;
import com.rlti.hexagonal.application.ports.in.InsertPessoaInputPort;
import com.rlti.hexagonal.application.ports.out.InsertPessoaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    private final InsertPessoaOutputPort insertPessoaOutputPort;

    @Bean
    public InsertPessoaInputPort insertPessoaInputPort() {
        return new InsertPessoaUserCase(insertPessoaOutputPort);
    }
}
