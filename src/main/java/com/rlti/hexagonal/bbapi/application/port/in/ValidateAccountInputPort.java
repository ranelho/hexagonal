package com.rlti.hexagonal.bbapi.application.port.in;

import com.rlti.hexagonal.bbapi.adapter.response.ValidationResponse;

public interface ValidateAccountInputPort {
    ValidationResponse validateAccount(String agencia, String conta, String cpfCnpj);
}
