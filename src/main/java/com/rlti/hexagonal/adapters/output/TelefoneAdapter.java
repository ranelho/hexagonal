package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.TelefoneRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaFisicaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.TelefoneEntity;
import com.rlti.hexagonal.application.core.domain.Pessoa;
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
    public void salvar(Telefone telefone) {
        var telefoneEntity = convertTelefone(telefone);
        telefoneRepository.save(telefoneEntity);
    }

    @Override
    public List<Telefone> findAllByPessoaFisica(Pessoa pessoa) {
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
