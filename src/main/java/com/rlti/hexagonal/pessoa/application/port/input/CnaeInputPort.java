package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.controller.request.CnaeRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.CnaeResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface CnaeInputPort {
    List<CnaeResponse> findAll();

    void addCnaes(Long idPessoa, @Valid List<CnaeRequest> cnaeRequestList);
}
