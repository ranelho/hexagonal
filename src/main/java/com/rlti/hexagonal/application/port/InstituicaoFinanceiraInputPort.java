package com.rlti.hexagonal.application.port;

import com.rlti.hexagonal.adapters.input.controller.request.AgenciaRequest;
import com.rlti.hexagonal.adapters.input.controller.request.BancoRequest;
import com.rlti.hexagonal.adapters.output.response.AgenciaResponse;
import com.rlti.hexagonal.adapters.output.response.BancoResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface InstituicaoFinanceiraInputPort {
    void criarBanco(@Valid BancoRequest request);

    void atualizarBanco(Long id, @Valid BancoRequest request);

    Page<BancoResponse> listarBancos(int page, int size);

    BancoResponse buscarBanco(Long id);

    void criarAgencia(Long id, @Valid AgenciaRequest request);

    void atualizarAgencia(Long idAgencia, @Valid AgenciaRequest request);

    AgenciaResponse buscarAgencia(Long idAgencia);

    Page<AgenciaResponse> listarAgenciasPorBanco(Long idBanco, int page, int size);
}
