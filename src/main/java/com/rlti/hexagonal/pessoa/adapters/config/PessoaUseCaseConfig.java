package com.rlti.hexagonal.pessoa.adapters.config;

import com.rlti.hexagonal.pessoa.application.core.usecase.PessoaUserCase;
import com.rlti.hexagonal.pessoa.application.port.output.PessoaOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaUseCaseConfig {

    @Bean
    public PessoaUserCase pessoaUserCase(PessoaOutputPort pessoaOutputPort, ModelMapper modelMapper) {
        return new PessoaUserCase(pessoaOutputPort, modelMapper);
    }
}

