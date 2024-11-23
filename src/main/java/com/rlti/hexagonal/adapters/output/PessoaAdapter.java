package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.PessoaFisicaRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaJuridicaRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.Tipo;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.handler.DuplicidadeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaAdapter implements PessoaOutputPort {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void salvar(Pessoa pessoa) {
        if (pessoa.getTipo() == Tipo.FISICA) {
            validarDuplicidadePessoaFisica(pessoa);
        } else if (pessoa.getTipo() == Tipo.JURIDICA) {
            validarDuplicidadePessoaJuridica(pessoa);
        }
        var pessoaEntity = new PessoaEntity(pessoa);
        pessoaRepository.save(pessoaEntity);
    }

    @Override
    public Page<Pessoa> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nome").ascending());
        Page<PessoaEntity> pessoaEntities = pessoaRepository.findAll(pageable);

        List<Pessoa> pessoas = pessoaEntities.getContent().stream()
                .map(entity -> modelMapper.map(entity, Pessoa.class))
                .toList();

        return new PageImpl<>(pessoas, pageable, pessoaEntities.getTotalElements());
    }

    private void validarDuplicidadePessoaJuridica(Pessoa pessoa) {
        pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + " já existe.");
                });
    }

    private void validarDuplicidadePessoaFisica(Pessoa pessoa) {
        pessoaFisicaRepository.findByCpf(pessoa.getPessoaFisica().getCpf())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CPF " + pessoa.getPessoaFisica().getCpf() + " já existe.");
                });
    }
}
