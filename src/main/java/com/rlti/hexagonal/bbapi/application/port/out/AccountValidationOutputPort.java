package com.rlti.hexagonal.bbapi.application.port.out;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;

public interface AccountValidationOutputPort {
    ValidateAccount validateAccount(String agencia, String conta, String cpfCnpj);
}
