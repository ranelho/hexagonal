package com.rlti.hexagonal.pessoa.application.port.output;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PessoaOutputPort {
    Long salvar(Pessoa pessoa);

    Page<Pessoa> findAllPaginated(int page, int size);

    PessoaFisica getByCpf(String cpf);

    PessoaFisica findById(Long idPessoa);

    Page<PessoaFisica> findAllPessoaFisicaPaginated(Pageable pageable);

    Pessoa findPessoaById(Long idPessoa);

    PessoaFisica findPessoaFisicaById(Long id);

    PessoaJuridica findPessoaJuridicaById(Long idPessoa);

    Page<PessoaJuridica> findAllPessoaJuridicaPaginated(int page, int size);

    void salvarEmail(Long idPessoa, Email email);

    void atualizarPessoa(Long idPessoa, PessoaFisica pessoa);

    Page<PessoaFisica> listPessoasFisicasWithFilter(Pageable pageable, PessoaFilterParam params);

    Long criarPessoaFisica(Pessoa pessoa);
}
