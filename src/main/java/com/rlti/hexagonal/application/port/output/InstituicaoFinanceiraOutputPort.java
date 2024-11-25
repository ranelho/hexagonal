package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.adapters.output.response.BancoResponse;
import com.rlti.hexagonal.application.core.domain.Agencia;
import com.rlti.hexagonal.application.core.domain.Banco;
import org.springframework.data.domain.Page;

public interface InstituicaoFinanceiraOutputPort {
    void criarBanco(Banco banco);

    void atualizarBanco(Long id, Banco banco);

    Page<Banco> findAllPaginated(int page, int size);

    Banco findById(Long id);

    void criarAgencia(Long id, Agencia agencia);

    void atualizarAgencia(Long idAgencia, Agencia agencia);

    Agencia buscarAgencia(Long idAgencia);

    Page<Agencia> listarAgenciasPorBanco(Long idBanco, int page, int size);
}
