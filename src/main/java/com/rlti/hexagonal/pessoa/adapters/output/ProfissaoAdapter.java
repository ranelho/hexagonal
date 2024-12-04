package com.rlti.hexagonal.pessoa.adapters.output;

import com.rlti.hexagonal.pessoa.adapters.output.repository.ProfissaoRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.entity.ProfissaoEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.Profissao;
import com.rlti.hexagonal.pessoa.application.port.output.ProfissaoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissaoAdapter implements ProfissaoOutputPort {

    private final ProfissaoRepository profissaoRepository;


    @Override
    public void processarProfissao(Profissao profissao) {
        if (!profissaoRepository.existsByCodigo(profissao.getCodigo())) {
            // Salva a profissão no banco de dados
            profissaoRepository.save(new ProfissaoEntity(profissao));
            System.out.println("Profissão salva: " + profissao.getCodigo() + " - " + profissao.getNomeProfissao());
        } else {
            // Se já existir, não faz nada ou pode logar um aviso
            System.out.println("Profissão já existe: " + profissao.getCodigo() + " - " + profissao.getNomeProfissao());
        }
    }
}
