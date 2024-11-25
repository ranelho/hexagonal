package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PessoaOutputPort {
    void salvar(Pessoa pessoa);

    Page<Pessoa> findAllPaginated(int page, int size);

    PessoaFisica getByCpf(String cpf);

    Pessoa findById(Long id);

    Page<PessoaFisica> findAllPessoaFisicaPaginated(int page, int size);
}
