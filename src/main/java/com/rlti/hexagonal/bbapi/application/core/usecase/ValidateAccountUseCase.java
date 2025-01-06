package com.rlti.hexagonal.bbapi.application.core.usecase;

import com.rlti.hexagonal.bbapi.adapter.response.ValidationResponse;
import com.rlti.hexagonal.bbapi.application.port.in.ValidateAccountInputPort;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationOutputPort;

public class ValidateAccountUseCase implements ValidateAccountInputPort {

    private final AccountValidationOutputPort validationOutputPort;

    public ValidateAccountUseCase(AccountValidationOutputPort validationOutputPort) {
        this.validationOutputPort = validationOutputPort;
    }

    @Override
    public ValidationResponse validateAccount(String agencia, String conta, String cpfCnpj) {
        var validateAccount = validationOutputPort.validateAccount(agencia, conta, cpfCnpj);
        return new ValidationResponse(validateAccount);
    }
}
