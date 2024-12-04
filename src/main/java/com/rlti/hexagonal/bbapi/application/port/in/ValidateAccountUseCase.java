package com.rlti.hexagonal.bbapi.application.port.in;

import com.rlti.hexagonal.bbapi.domain.model.ValidationResponse;

public interface ValidateAccountUseCase {
    ValidationResponse validateAccount(String agencia, String conta, String cpfCnpj);
}
