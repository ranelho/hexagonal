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
    public void salvar(Telefone telefone) {
        var telefoneEntity = modelMapper.map(telefone, TelefoneEntity.class);
        telefoneRepository.save(telefoneEntity);
    }

    @Override
    public List<Telefone> findAllByPessoaFisica(PessoaFisica pessoaFisica) {
        var pessoaFisicaEntity = modelMapper.map(pessoaFisica, PessoaFisicaEntity.class);
        var telefoneEntity = telefoneRepository.findAllByPessoaFisica(pessoaFisicaEntity);
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
}
