package com.rlti.hexagonal.pessoa.application.port.input;

import com.rlti.hexagonal.pessoa.adapters.input.request.AgenciaRequest;
import com.rlti.hexagonal.pessoa.adapters.input.request.BancoRequest;
import com.rlti.hexagonal.pessoa.adapters.output.response.AgenciaResponse;
import com.rlti.hexagonal.pessoa.adapters.output.response.BancoResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InstituicaoFinanceiraInputPort {
    void criarBanco(@Valid BancoRequest request);

    void atualizarBanco(Long id, @Valid BancoRequest request);

    Page<BancoResponse> listarBancos(int page, int size);

    BancoResponse buscarBanco(Long id);

    void criarAgencia(Long id, @Valid AgenciaRequest request);

    void atualizarAgencia(Long idAgencia, @Valid AgenciaRequest request);

    AgenciaResponse buscarAgencia(Long idAgencia);

    Page<AgenciaResponse> listarAgenciasPorBanco(Long idBanco, int page, int size);

    void importExcel(MultipartFile file) throws IOException;
}
