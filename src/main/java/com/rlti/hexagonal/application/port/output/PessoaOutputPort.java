package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import org.springframework.data.domain.Page;


public interface PessoaOutputPort {
    void salvar(Pessoa pessoa);

    Page<Pessoa> findAllPaginated(int page, int size);

    PessoaFisica getByCpf(String cpf);

    PessoaFisica findById(Long id);

    Page<PessoaFisica> findAllPessoaFisicaPaginated(int page, int size);

    Pessoa findPessoaById(Long id);
}
