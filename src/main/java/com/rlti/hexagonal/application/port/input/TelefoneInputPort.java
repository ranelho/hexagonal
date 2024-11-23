package com.rlti.hexagonal.application.port.input;

import com.rlti.hexagonal.adapters.input.controller.request.TelefoneRequest;
import com.rlti.hexagonal.adapters.output.response.TelefoneResponse;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public interface TelefoneInputPort {
    void criarTelefone(String cpf, TelefoneRequest request);

    List<TelefoneResponse> findAllByCpf(@CPF String cpf);

    void updateTelefone(Long id, @Valid TelefoneRequest request);

    void deleteTelefone(Long id);
}
