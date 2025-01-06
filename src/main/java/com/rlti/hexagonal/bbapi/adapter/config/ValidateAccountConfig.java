package com.rlti.hexagonal.bbapi.adapter.config;

import com.rlti.hexagonal.bbapi.application.core.usecase.ValidateAccountUseCase;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateAccountConfig {

    @Bean
    public ValidateAccountUseCase validateAccountUseCase(AccountValidationOutputPort validationOutputPort) {
        return new ValidateAccountUseCase(validationOutputPort);
    }
}
