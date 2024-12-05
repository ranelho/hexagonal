package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.TelefoneResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface TelefoneInputPort {
    void criarTelefone(Long id, List<TelefoneRequest> telefones);

    List<TelefoneResponse> findAllById(Long idPessoa);

    void updateTelefone(Long id, @Valid TelefoneRequest request);

    void deleteTelefone(Long id);
}
