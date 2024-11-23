package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;

public interface TelefoneInputPort {
    void criarTelefone(String cpf, TelefoneRequest request);
}
