package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.IbgeClient;
import com.rlti.hexagonal.pessoa.adapters.output.repository.CnaeRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaJuridicaCnaeRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaJuridicaRepository;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaCnaeEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CnaeAdapter implements CnaeOutputPort {
    private final CnaeRepository cnaeRepository;
    private final ModelMapper modelMapper;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaJuridicaCnaeRepository pessoaJuridicaCnaeRepository;
    private final IbgeClient ibgeClient;


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

    @Override
    public void processarCnae(Cnae cnae) {
        if (!cnaeRepository.existsBySubclasse(cnae.getSubclasse())) cnaeRepository.save(new CnaeEntity(cnae));
    }

    public void salvarSubclasses() {
        List<Map<String, Object>> response = ibgeClient.getSubclasses();
        if (response != null) {
            Set<String> idsUnicos = new HashSet<>();
            List<CnaeEntity> subclasses = new ArrayList<>();

            for (Map<String, Object> item : response) {
                String id = (String) item.get("id");
                String descricao = (String) item.get("descricao");
                if (!idsUnicos.contains(id) && !cnaeRepository.existsBySubclasse(id)) {
                    idsUnicos.add(id);
                    CnaeEntity subclasse = new CnaeEntity();
                    subclasse.setSubclasse(id);
                    subclasse.setDenominacao(descricao);
                    subclasses.add(subclasse);
                }
            }
            if (!subclasses.isEmpty()) {
                cnaeRepository.saveAll(subclasses);
                log.info("Subclasses salvas com sucesso.");
            } else {
                log.info("Nenhuma nova subclasse encontrada para salvar.");
            }
        }
    }

}
