package com.rlti.hexagonal.bbapi.application.service;

import com.rlti.hexagonal.bbapi.application.port.in.ValidateAccountUseCase;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationService;
import com.rlti.hexagonal.bbapi.application.port.out.TokenProvider;
import com.rlti.hexagonal.bbapi.domain.model.ValidationResponse;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationServiceImpl implements ValidateAccountUseCase {

    private final TokenProvider tokenProvider;
    private final AccountValidationService validationService;

    public AccountValidationServiceImpl(TokenProvider tokenProvider, AccountValidationService validationService) {
        this.tokenProvider = tokenProvider;
        this.validationService = validationService;
    }

    @Override
    public ValidationResponse validateAccount(String agencia, String conta, String cpfCnpj) {
        String token = tokenProvider.getOAuthToken();
        return validationService.validateAccount(token, agencia, conta, cpfCnpj);
    }
}
