package com.rlti.hexagonal.bbapi.adapter.service;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationOutputPort;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationService;
import com.rlti.hexagonal.bbapi.application.port.out.TokenProvider;
import org.springframework.stereotype.Service;

@Service
public class AccountValidationServiceImpl implements AccountValidationOutputPort {

    private final TokenProvider tokenProvider;
    private final AccountValidationService validationService;

    public AccountValidationServiceImpl(TokenProvider tokenProvider, AccountValidationService validationService) {
        this.tokenProvider = tokenProvider;
        this.validationService = validationService;
    }

    @Override
    public ValidateAccount validateAccount(String agencia, String conta, String cpfCnpj) {
        String token = tokenProvider.getOAuthToken();
        return validationService.validateAccount(token, agencia, conta, cpfCnpj);
    }
}
