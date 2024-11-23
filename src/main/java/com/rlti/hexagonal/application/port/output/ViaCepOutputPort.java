package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Endereco;

public interface ViaCepOutputPort {
    Endereco buscarEndereco(String cep);
}
