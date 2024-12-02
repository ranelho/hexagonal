package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.ContaBancariaRequest;
import jakarta.validation.Valid;

public interface ContaBancariaInputPort {

    void novaContaBancaria(Long idPessoa, @Valid ContaBancariaRequest request);
}
