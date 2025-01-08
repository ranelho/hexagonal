package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;

public interface ContaBancariaOutputPort {
    void novaContaBancaria(ContaBancaria contaBancaria);

}
