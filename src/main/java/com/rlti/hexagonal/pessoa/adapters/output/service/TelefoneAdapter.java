package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.repository.TelefoneRepository;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.TelefoneEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Telefone;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.port.output.TelefoneOutputPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefoneAdapter implements TelefoneOutputPort {

    private final TelefoneRepository telefoneRepository;
    private final ModelMapper modelMapper;

    @Override
    public void salvarTodos(List<Telefone> telefones) {
        var telefoneEntity = telefones.stream()
                .map(this::convertTelefone)
                .toList();
        var pessoaEntity = modelMapper.map(telefoneEntity.get(0).getPessoa(), PessoaEntity.class);
        var telefonesEntity = telefoneRepository.findAllByPessoa(pessoaEntity);
        //criar validaçao para verificar se o telefone ja existe
        telefoneRepository.saveAll(telefoneEntity);
    }

    @Override
    public void salvar(Telefone telefones) {
        var telefoneEntity = convertTelefone(telefones);
        var pessoaEntity = modelMapper.map(telefones.getPessoa(), PessoaEntity.class);
        var telefonesEntity = telefoneRepository.findAllByPessoa(pessoaEntity);
        if (pessoaEntity.getTipo() == Tipo.FISICA && telefonesEntity.size() > 2) {
            throw new RuntimeException("Pessoa Fisica não pode ter mais de 3 telefones");
        }
        telefoneRepository.save(telefoneEntity);
    }

    @Override
    public List<Telefone> findAllByPessoa(Pessoa pessoa) {
        var pessoaEntity = modelMapper.map(pessoa, PessoaEntity.class);
        var telefoneEntity = telefoneRepository.findAllByPessoa(pessoaEntity);
        return telefoneEntity.stream()
                .map(entity -> modelMapper.map(entity, Telefone.class))
                .toList();
    }

    @Override
    public Telefone getById(Long id) {
        var telefoneEntity = telefoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado com ID: " + id));
        return modelMapper.map(telefoneEntity, Telefone.class);
    }

    @Override
    public void delete(Telefone telefone) {
        var telefoneEntity = convertTelefone(telefone);
        telefoneRepository.delete(telefoneEntity);
    }

    private TelefoneEntity convertTelefone(Telefone telefone) {
        return modelMapper.map(telefone, TelefoneEntity.class);
    }
}
