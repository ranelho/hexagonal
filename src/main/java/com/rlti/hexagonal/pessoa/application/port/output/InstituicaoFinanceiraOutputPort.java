package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Agencia;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Banco;
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

    void save(Banco banco);
}
