package com.rlti.hexagonal.adapters.input.controller.request;

import com.rlti.hexagonal.application.core.domain.enums.TipoEmail;
import lombok.Data;

@Data
public class EmailRequest {
    private String email;
    private TipoEmail tipoEmail;
}
