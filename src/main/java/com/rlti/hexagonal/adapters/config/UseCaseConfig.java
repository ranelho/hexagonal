package com.rlti.hexagonal.adapters.config;

import com.rlti.hexagonal.adapters.output.ViaCepFeignAdapter;
import com.rlti.hexagonal.application.core.usecase.BuscaEnderecoUseCase;
import com.rlti.hexagonal.application.core.usecase.PessoaUserCase;
import com.rlti.hexagonal.application.port.input.PessoaInputPort;
import com.rlti.hexagonal.application.port.output.BuscaEnderecoInputPort;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.application.port.output.ViaCepOutputPort;
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

    @Bean
    public BuscaEnderecoInputPort buscaEnderecoUseCase(ViaCepOutputPort viaCepOutputPort) {
        return new BuscaEnderecoUseCase(viaCepOutputPort);
    }

    @Bean
    public ViaCepOutputPort viaCepOutputPort(ViaCepFeignAdapter viaCepFeignAdapter) {
        return viaCepFeignAdapter;
    }
}
