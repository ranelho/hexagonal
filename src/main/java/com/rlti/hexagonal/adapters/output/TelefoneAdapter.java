package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.TelefoneRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.TelefoneEntity;
import com.rlti.hexagonal.application.core.domain.Telefone;
import com.rlti.hexagonal.application.port.output.TelefoneOutputPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
