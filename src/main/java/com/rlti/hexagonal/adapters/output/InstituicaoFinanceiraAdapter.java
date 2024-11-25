package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.AgenciaRepository;
import com.rlti.hexagonal.adapters.output.repository.BancoRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.AgenciaEntity;
import com.rlti.hexagonal.adapters.output.repository.entity.BancoEntity;
import com.rlti.hexagonal.application.core.domain.Agencia;
import com.rlti.hexagonal.application.core.domain.Banco;
import com.rlti.hexagonal.application.port.output.InstituicaoFinanceiraOutputPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstituicaoFinanceiraAdapter implements InstituicaoFinanceiraOutputPort {

    private final BancoRepository bancoRepository;
    private final AgenciaRepository agenciaRepository;
    private final ModelMapper modelMapper;

    @Override
    public void criarBanco(Banco banco) {
        var bancoEntity = modelMapper.map(banco, BancoEntity.class);
        bancoRepository.save(bancoEntity);
    }

    @Override
    public void atualizarBanco(Long id, Banco banco) {
        var bancoEntity = getBancoEntity(id);
        bancoEntity.update(banco);
        bancoRepository.save(bancoEntity);
    }

    @Override
    public Page<Banco> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("codigo").ascending());
        var bancosEntities = bancoRepository.findAll(pageable);
        var bancos = bancosEntities.getContent().stream()
                .map(entity -> modelMapper.map(entity, Banco.class))
                .toList();
        return new PageImpl<>(bancos, pageable, bancosEntities.getTotalElements());
    }

    @Override
    public Banco findById(Long id) {
        var bancoEntity = getBancoEntity(id);
        return modelMapper.map(bancoEntity, Banco.class);
    }

    @Override
    public void criarAgencia(Long id, Agencia agencia) {
        var bancoEntity = getBancoEntity(id);
        agenciaRepository.save(new AgenciaEntity(bancoEntity, agencia));
    }

    @Override
    public void atualizarAgencia(Long idAgencia, Agencia agencia) {
        var agenciaEntity = agenciaRepository.getReferenceById(idAgencia);
        agenciaEntity.update(agencia);
        agenciaRepository.save(agenciaEntity);
    }

    @Override
    public Agencia buscarAgencia(Long idAgencia) {
        var agenciaEntity = agenciaRepository.findById(idAgencia).orElseThrow(
                () -> new RuntimeException("Agência não encontrada"));
        return modelMapper.map(agenciaEntity, Agencia.class);
    }

    @Override
    public Page<Agencia> listarAgenciasPorBanco(Long idBanco, int page, int size) {
        var bancoEntity = getBancoEntity(idBanco);
        Pageable pageable = PageRequest.of(page, size, Sort.by("agencia").ascending());
        var agenciasEntity = agenciaRepository.findAllByBanco(bancoEntity, pageable);
        var agencias = agenciasEntity.getContent().stream()
                .map(entity -> modelMapper.map(entity, Agencia.class))
                .toList();
        return new PageImpl<>(agencias, pageable, agenciasEntity.getTotalElements());
    }

    private BancoEntity getBancoEntity(Long id) {
        return bancoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Banco não encontrado"));
    }
}
