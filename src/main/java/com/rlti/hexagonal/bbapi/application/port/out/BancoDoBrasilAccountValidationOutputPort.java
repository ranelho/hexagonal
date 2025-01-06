package com.rlti.hexagonal.bbapi.application.port.out;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;

public interface BancoDoBrasilAccountValidationOutputPort {
    ValidateAccount validateAccount(String token, String agencia, String conta, String cpfCnpj);
}
