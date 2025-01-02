package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.input.request.ProfissaoFilter;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.ProfissaoEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.ProfissaoRepository;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.Profissao;
import com.rlti.hexagonal.pessoa.application.port.output.ProfissaoOutputPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissaoAdapter implements ProfissaoOutputPort {

    private final ProfissaoRepository profissaoRepository;
    private final ModelMapper modelMapper;


    @Override
    public void processarProfissao(Profissao profissao) {
        if (!profissaoRepository.findByNumeroProfissao(profissao.getNumeroProfissao())) {
            // Salva a profissão no banco de dados
            profissaoRepository.save(new ProfissaoEntity(profissao));
            System.out.println("Profissão salva: " + profissao.getNumeroProfissao() + " - " + profissao.getNomeProfissao());
        } else {
            // Se já existir, não faz nada ou pode logar um aviso
            System.out.println("Profissão já existe: " + profissao.getNumeroProfissao() + " - " + profissao.getNomeProfissao());
        }
    }

    @Override
    public List<Profissao> getAllProfissoesWithFilter(ProfissaoFilter params) {
        return profissaoRepository.getWithFilter(params)
                .stream()
                .map(entity -> modelMapper.map(entity, Profissao.class))
                .toList();
    }
}
