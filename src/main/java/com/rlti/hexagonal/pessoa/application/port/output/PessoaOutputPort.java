package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.application.core.domain.Pessoa;
import com.rlti.hexagonal.pessoa.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.PessoaJuridica;
import org.springframework.data.domain.Page;


public interface PessoaOutputPort {
    void salvar(Pessoa pessoa);

    Page<Pessoa> findAllPaginated(int page, int size);

    PessoaFisica getByCpf(String cpf);

    PessoaFisica findById(Long idPessoa);

    Page<PessoaFisica> findAllPessoaFisicaPaginated(int page, int size);

    Pessoa findPessoaById(Long idPessoa);

    PessoaFisica findPessoaFisicaById(Long id);

    PessoaJuridica findPessoaJuridicaById(Long idPessoa);

    Page<PessoaJuridica> findAllPessoaJuridicaPaginated(int page, int size);
}
