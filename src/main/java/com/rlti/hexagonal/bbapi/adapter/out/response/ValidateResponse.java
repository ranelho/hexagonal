package com.rlti.hexagonal.bbapi.adapter.out.response;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;

public record ValidateResponse(String contaAtiva, String titularConta) {
    public ValidateResponse(ValidateAccount validateAccount) {
        this(validateAccount.getContaAtiva(), validateAccount.getTitularConta());
    }
}
