package com.rlti.hexagonal.bbapi.adapter.response;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;

public record ValidationResponse(String contaAtiva, String titularConta) {
    public ValidationResponse(ValidateAccount validateAccount) {
        this(validateAccount.getContaAtiva(), validateAccount.getTitularConta());
    }
}
