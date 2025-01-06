package com.rlti.hexagonal.bbapi.application.port.in;

import com.rlti.hexagonal.bbapi.adapter.out.response.ValidateResponse;

public interface ValidateAccountInputPort {
    ValidateResponse validateAccount(String agencia, String conta, String cpfCnpj);
}
