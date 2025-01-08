package com.rlti.hexagonal.pessoa.adapters.input.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEmail;

public record EmailRequest(String email, TipoEmail tipoEmail) {
}
