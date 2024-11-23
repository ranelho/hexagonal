package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Telefone;

public interface TelefoneOutputPort {
    void salvar(Telefone telefone);
}
