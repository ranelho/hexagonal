package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Banco;

public interface InstituicaoFinanceiraOutputPort {
    void criarBanco(Banco banco);

    void atualizarBanco(Long id, Banco banco);
}
