package com.rlti.hexagonal.adapters.out;

import com.rlti.hexagonal.adapters.out.repository.PessoaFisicaRepository;
import com.rlti.hexagonal.adapters.out.repository.PessoaRepository;
import com.rlti.hexagonal.adapters.out.repository.entity.PessoaEntity;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.out.PessoaOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaAdapter implements PessoaOutputPort {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    @Transactional
    public void salvar(Pessoa pessoa) {
        var pessoaEntity = new PessoaEntity(pessoa);
        pessoaRepository.save(pessoaEntity);
    }
}
