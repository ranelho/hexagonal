package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.IbgeClient;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaCnaeEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.CnaeRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaJuridicaCnaeRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaJuridicaRepository;
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
        // Busca a pessoa jurídica pelo ID
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Jurídica não encontrada para o ID: " + idPessoa));

        // Converte os CNAEs recebidos para as entidades
        var cnaeEntities = cnaes.stream()
                .map(cnae -> cnaeRepository.findBySubclasse(cnae.getSubclasse())
                        .orElseThrow(() -> new EntityNotFoundException("CNAE não encontrado para o código: " + cnae.getSubclasse())))
                .toList();

        // Obtém os relacionamentos existentes
        var cnaesExistentes = pessoaJuridicaEntity.getCnaes();

        // Itera sobre os CNAEs recebidos
        for (var cnaeEntity : cnaeEntities) {
            var relacionamentoExistente = cnaesExistentes.stream()
                    .filter(rel -> rel.getCnae().getSubclasse().equals(cnaeEntity.getSubclasse()))
                    .findFirst();

            // Verifica se o CNAE já está cadastrado
            boolean isPrincipal = cnaes.stream()
                    .anyMatch(cnae -> cnae.isPrincipal() && cnae.getSubclasse().equals(cnaeEntity.getSubclasse()));

            if (relacionamentoExistente.isPresent()) {
                // Atualiza o status de principal se já existir
                relacionamentoExistente.get().setPrincipal(isPrincipal);
            } else {
                // Adiciona um novo relacionamento caso não exista
                var novoRelacionamento = new PessoaJuridicaCnaeEntity();
                novoRelacionamento.setPessoaJuridica(pessoaJuridicaEntity);
                novoRelacionamento.setCnae(cnaeEntity);
                novoRelacionamento.setPrincipal(isPrincipal);
                cnaesExistentes.add(novoRelacionamento);
            }
        }

        // Garante que apenas um CNAE esteja marcado como principal
        var cnaesPrincipais = cnaesExistentes.stream()
                .filter(PessoaJuridicaCnaeEntity::isPrincipal)
                .toList();

        if (cnaesPrincipais.size() > 1) {
            // Define o mais recente como principal e remove a flag dos outros
            PessoaJuridicaCnaeEntity principalAtual = cnaesPrincipais.get(0);
            for (var principal : cnaesPrincipais) {
                if (!principal.equals(principalAtual)) {
                    principal.setPrincipal(false);
                }
            }
        }

        // Salva as alterações na entidade
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

    @Transactional
    @Override
    public void addCnaesV2(Long idPessoa, String cnaePrincipal, List<Cnae> cnaes) {
        // Busca a pessoa jurídica pelo ID
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa Jurídica não encontrada para o ID: " + idPessoa));

        // Busca o CNAE principal pelo código da subclasse
        var cnaePrincipalEntity = cnaeRepository.findBySubclasse(cnaePrincipal)
                .orElseThrow(() -> new EntityNotFoundException("CNAE principal não encontrado para o código: " + cnaePrincipal));

        // Converte os CNAEs secundários recebidos para as entidades
        var cnaeSecundariosEntities = cnaes.stream()
                .map(cnae -> cnaeRepository.findBySubclasse(cnae.getSubclasse())
                        .orElseThrow(() -> new EntityNotFoundException("CNAE secundário não encontrado para o código: " + cnae.getSubclasse())))
                .toList();

        // Obtém os relacionamentos existentes
        var cnaesExistentes = pessoaJuridicaEntity.getCnaes();

        // Atualiza ou adiciona o CNAE principal
        var principalExistente = cnaesExistentes.stream()
                .filter(PessoaJuridicaCnaeEntity::isPrincipal)
                .findFirst();

        if (principalExistente.isPresent()) {
            // Se já existir um principal, atualiza o CNAE para o novo principal
            var relacaoPrincipal = principalExistente.get();
            if (!relacaoPrincipal.getCnae().getSubclasse().equals(cnaePrincipal)) {
                relacaoPrincipal.setCnae(cnaePrincipalEntity);
            }
        } else {
            // Se não existir, cria o relacionamento para o principal
            var novoPrincipal = new PessoaJuridicaCnaeEntity();
            novoPrincipal.setPessoaJuridica(pessoaJuridicaEntity);
            novoPrincipal.setCnae(cnaePrincipalEntity);
            novoPrincipal.setPrincipal(true);
            cnaesExistentes.add(novoPrincipal);
        }

        // Adiciona ou mantém os CNAEs secundários
        for (var cnaeSecundario : cnaeSecundariosEntities) {
            var relacaoExistente = cnaesExistentes.stream()
                    .filter(rel -> rel.getCnae().getSubclasse().equals(cnaeSecundario.getSubclasse()))
                    .findFirst();

            if (relacaoExistente.isEmpty()) {
                // Se o secundário não existir, cria um novo relacionamento
                var novoSecundario = new PessoaJuridicaCnaeEntity();
                novoSecundario.setPessoaJuridica(pessoaJuridicaEntity);
                novoSecundario.setCnae(cnaeSecundario);
                novoSecundario.setPrincipal(false);
                cnaesExistentes.add(novoSecundario);
            }
        }

        // Salva as alterações
        pessoaJuridicaRepository.save(pessoaJuridicaEntity);
    }


}
