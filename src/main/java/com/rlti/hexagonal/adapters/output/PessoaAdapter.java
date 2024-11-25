package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.PessoaFisicaRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaJuridicaRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaEntity;
import com.rlti.hexagonal.application.core.domain.Tipo;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
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
        //var pessoaEntity = modelMapper.map(pessoa, PessoaEntity.class);
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

    @Override
    public PessoaFisica getByCpf(String cpf) {
        var pessoaEntity = pessoaFisicaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com CPF: " + cpf));
        return modelMapper.map(pessoaEntity, PessoaFisica.class);
    }

    @Override
    public Pessoa findById(Long id) {
        var pessoaEntity = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
        return modelMapper.map(pessoaEntity, Pessoa.class);
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
