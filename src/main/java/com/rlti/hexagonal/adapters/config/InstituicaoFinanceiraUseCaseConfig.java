package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.application.core.usecase.InstituicaoFinanceiraUseCase;
import com.rlti.hexagonal.application.core.usecase.TelefoneUseCase;
import com.rlti.hexagonal.application.port.output.InstituicaoFinanceiraOutputPort;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.application.port.output.TelefoneOutputPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstituicaoFinanceiraUseCaseConfig {

    @Bean
    public InstituicaoFinanceiraUseCase instituicaoFinanceiraUseCase(InstituicaoFinanceiraOutputPort instituicaoFinanceiraOutputPort, ModelMapper modelMapper) {
        return new InstituicaoFinanceiraUseCase(instituicaoFinanceiraOutputPort, modelMapper);
    }

}

