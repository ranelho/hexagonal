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
        // Busca a entidade PessoaJuridica pelo ID
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Jurídica não encontrada para o ID: " + idPessoa));

        // Converte os CNAEs recebidos para as entidades do banco
        var cnaeEntities = cnaes.stream()
                .map(cnae -> cnaeRepository.findBySubclasse(cnae.getSubclasse())
                        .orElseThrow(() -> new EntityNotFoundException("CNAE não encontrado para o código: " + cnae.getSubclasse())))
                .toList();

        // Cria e associa os relacionamentos na entidade intermediária
        List<PessoaJuridicaCnaeEntity> relacionamentos = cnaeEntities.stream()
                .map(cnaeEntity -> {
                    PessoaJuridicaCnaeEntity relacao = new PessoaJuridicaCnaeEntity();
                    relacao.setPessoaJuridica(pessoaJuridicaEntity);
                    relacao.setCnae(cnaeEntity);

                    // Define o principal (se necessário, você pode implementar lógica para isso)
                    relacao.setPrincipal(cnaes.stream()
                            .anyMatch(cnae -> cnae.getPrincipal() && cnae.getSubclasse().equals(cnaeEntity.getSubclasse())));

                    return relacao;
                })
                .toList();

        // Adiciona os relacionamentos à Pessoa Jurídica
        pessoaJuridicaEntity.getCnaes().addAll(relacionamentos);

        // Salva a Pessoa Jurídica com os relacionamentos no banco
        pessoaJuridicaRepository.save(pessoaJuridicaEntity);
    }


}
