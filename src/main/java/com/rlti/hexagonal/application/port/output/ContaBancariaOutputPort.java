package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.ContaBancaria;

public interface ContaBancariaOutputPort {
    void novaContaBancaria(Long idPessoa, ContaBancaria contaBancaria);
}
