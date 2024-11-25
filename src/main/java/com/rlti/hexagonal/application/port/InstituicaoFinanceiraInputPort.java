package com.rlti.hexagonal.application.port;

import com.rlti.hexagonal.adapters.input.controller.request.BancoRequest;
import jakarta.validation.Valid;

public interface InstituicaoFinanceiraInputPort {
    void criarBanco(@Valid BancoRequest request);

    void atualizarBanco(Long id, @Valid BancoRequest request);
}
