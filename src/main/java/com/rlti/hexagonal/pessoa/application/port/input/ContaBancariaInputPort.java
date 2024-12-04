package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.ContaBancariaRequest;
import jakarta.validation.Valid;

public interface ContaBancariaInputPort {

    void novaContaBancaria(Long idPessoa, @Valid ContaBancariaRequest request);
}
