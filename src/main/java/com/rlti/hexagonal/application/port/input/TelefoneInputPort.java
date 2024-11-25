package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.adapters.output.response.TelefoneResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface TelefoneInputPort {
    void criarTelefone(Long id, TelefoneRequest request);

    List<TelefoneResponse> findAllByCpf(Long id);

    void updateTelefone(Long id, @Valid TelefoneRequest request);

    void deleteTelefone(Long id);
}
