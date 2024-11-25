package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.BancoRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.BancoEntity;
import com.rlti.hexagonal.application.core.domain.Banco;
import com.rlti.hexagonal.application.port.output.InstituicaoFinanceiraOutputPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstituicaoFinanceiraAdapter implements InstituicaoFinanceiraOutputPort {

    private final BancoRepository bancoRepository;
    private final ModelMapper modelMapper;

    @Override
    public void criarBanco(Banco banco) {
        var bancoEntity = modelMapper.map(banco, BancoEntity.class);
        bancoRepository.save(bancoEntity);
    }

    @Override
    public void atualizarBanco(Long id, Banco banco) {
        var bancoEntity =  bancoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Banco não encontrado"));
        bancoEntity.update(banco);
        bancoRepository.save(bancoEntity);
    }
}
