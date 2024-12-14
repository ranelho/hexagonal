package com.rlti.hexagonal.pessoa.adapters.output.repository;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryCustom {
    Page<PessoaFisica> findWithFilters(PessoaFilterParam params, Pageable pageable);
}
