package com.rlti.hexagonal.bbapi.adapter.out.service;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationOutputPort;
import com.rlti.hexagonal.bbapi.application.port.out.BancoDoBrasilAccountValidationOutputPort;
import com.rlti.hexagonal.bbapi.application.port.out.TokenProvider;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationService implements AccountValidationOutputPort {

    private final TokenProvider tokenProvider;
    private final BancoDoBrasilAccountValidationOutputPort validationService;

    public AccountValidationService(TokenProvider tokenProvider, BancoDoBrasilAccountValidationOutputPort validationService) {
        this.tokenProvider = tokenProvider;
        this.validationService = validationService;
    }

    @Override
    public ValidateAccount validateAccount(String agencia, String conta, String cpfCnpj) {
        String token = tokenProvider.getOAuthToken();
        return validationService.validateAccount(token, agencia, conta, cpfCnpj);
    }
}
