package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.TelefoneRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaFisicaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.TelefoneEntity;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.Telefone;
import com.rlti.hexagonal.application.port.output.TelefoneOutputPort;
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
        var pessoaEntity = modelMapper.map(telefones.get(0).getPessoaFisica(), PessoaFisicaEntity.class);
        var telefonesEntity = telefoneRepository.findAllByPessoaFisica(pessoaEntity);
        if (telefonesEntity.size() > 0) {
            throw new RuntimeException("Já existe um telefone cadastrado para a pessoa física");
        }
        telefoneRepository.saveAll(telefoneEntity);
    }

    @Override
    public void salvar(Telefone telefones) {
        var telefoneEntity = convertTelefone(telefones);
        var pessoaEntity = modelMapper.map(telefones.getPessoaFisica(), PessoaFisicaEntity.class);
        var telefonesEntity = telefoneRepository.findAllByPessoaFisica(pessoaEntity);
        if (telefonesEntity.size() > 0) {
            throw new RuntimeException("Já existe um telefone cadastrado para a pessoa física");
        }
        telefoneRepository.save(telefoneEntity);
    }

    @Override
    public List<Telefone> findAllByPessoaFisica(PessoaFisica pessoaFisica) {
        var pessoaEntity = modelMapper.map(pessoaFisica, PessoaFisicaEntity.class);
        var telefoneEntity = telefoneRepository.findAllByPessoaFisica(pessoaEntity);
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
