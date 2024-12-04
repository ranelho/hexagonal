package com.rlti.hexagonal.pessoa.adapters.input.controller.request;

import com.rlti.hexagonal.pessoa.application.core.domain.enums.TipoEmail;
import lombok.Data;

@Data
public class EmailRequest {
    private String email;
    private TipoEmail tipoEmail;
}
