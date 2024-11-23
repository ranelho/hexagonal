package com.rlti.hexagonal.application.port.output;

import com.rlti.hexagonal.application.core.domain.Pessoa;
import org.springframework.data.domain.Page;


public interface PessoaOutputPort {
    void salvar(Pessoa pessoa);

    Page<Pessoa> findAllPaginated(int page, int size);
}
