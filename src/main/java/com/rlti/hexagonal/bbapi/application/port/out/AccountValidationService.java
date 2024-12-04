package com.rlti.hexagonal.bbapi.application.port.out;

import com.rlti.hexagonal.bbapi.domain.model.ValidationResponse;

public interface AccountValidationService {
    ValidationResponse validateAccount(String token, String agencia, String conta, String cpfCnpj);
}
