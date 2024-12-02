package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.input.controller.request.CnaeRequest;
import com.rlti.hexagonal.adapters.output.repository.CnaeRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaJuridicaCnaeRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaJuridicaRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.PessoaJuridicaCnaeEntity;
import com.rlti.hexagonal.application.core.domain.Cnae;
import com.rlti.hexagonal.application.port.output.CnaeOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CnaeAdapter implements CnaeOutputPort {
    private final CnaeRepository cnaeRepository;
    private final ModelMapper modelMapper;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaJuridicaCnaeRepository pessoaJuridicaCnaeRepository;


    @Override
    public List<Cnae> findAll() {
        return cnaeRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, Cnae.class))
                .toList();
    }

    @Transactional
    @Override
    public void addCnaes(Long idPessoa, List<Cnae> cnaes) {
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Jurídica não encontrada para o ID: " + idPessoa));

        var cnaeEntities = cnaes.stream()
                .map(cnae -> cnaeRepository.findBySubclasse(cnae.getSubclasse())
                        .orElseThrow(() -> new EntityNotFoundException("CNAE não encontrado para o código: " + cnae.getSubclasse())))
                .toList();

        List<PessoaJuridicaCnaeEntity> relacionamentos = cnaeEntities.stream()
                .map(cnaeEntity -> {
                    PessoaJuridicaCnaeEntity relacao = new PessoaJuridicaCnaeEntity();
                    relacao.setPessoaJuridica(pessoaJuridicaEntity);
                    relacao.setCnae(cnaeEntity);
                    relacao.setPrincipal(cnaes.stream()
                            .anyMatch(cnae -> cnae.isPrincipal() && cnae.getSubclasse().equals(cnaeEntity.getSubclasse())));
                    return relacao;
                })
                .toList();
        pessoaJuridicaEntity.getCnaes().addAll(relacionamentos);
        pessoaJuridicaRepository.save(pessoaJuridicaEntity);
    }


}
