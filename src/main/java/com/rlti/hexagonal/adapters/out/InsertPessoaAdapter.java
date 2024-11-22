package com.rlti.hexagonal.adapters.out;

import com.rlti.hexagonal.adapters.out.entity.PessoaEntity;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.out.InsertPessoaOutputPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsertPessoaAdapter implements InsertPessoaOutputPort {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    @Override
    public void insert(Pessoa pessoa) {
        var pessoaEntity = modelMapper.map(pessoa, PessoaEntity.class);
        pessoaRepository.save(pessoaEntity);
    }
}
