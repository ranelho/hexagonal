package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.ContaBancaria;

public interface ContaBancariaOutputPort {
    void novaContaBancaria(Long idPessoa, ContaBancaria contaBancaria);
}
