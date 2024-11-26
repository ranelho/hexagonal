package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.*;
import com.rlti.hexagonal.adapters.output.repository.entity.*;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.handler.DuplicidadeException;
import com.rlti.hexagonal.handler.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaAdapter implements PessoaOutputPort {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final ModelMapper modelMapper;
    private final PaisRepository paisRepository;
    private final OrientacaoSexualRepository orientacaoSexualRepository;
    private final IdentidadeGeneroRepository identidadeGeneroRepository;

    @Override
    @Transactional
    public void salvar(Pessoa pessoa) {
        validarPessoa(pessoa);

        if (pessoa.getTipo() == Tipo.FISICA) {
            verificarDuplicidadeCpf(pessoa.getPessoaFisica().getCpf());

            var pais = buscarPaisPorId(pessoa.getPessoaFisica().getPaisId());
            var orientacao = buscarOrientacaoSexualPorId(pessoa.getPessoaFisica().getOrientacaoSexualId());
            var identidadeGenero = buscarIdentidadeGeneroPorId(pessoa.getPessoaFisica().getIdentidadeDeGeneroId());

            var pessoaEntity = new PessoaEntity(pessoa, pais, orientacao, identidadeGenero);
            pessoaRepository.save(pessoaEntity);
        }
    }

    private void validarPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        }
        if (pessoa.getTipo() == null) {
            throw new IllegalArgumentException("Tipo da pessoa deve ser informado.");
        }
    }

    private void verificarDuplicidadeCpf(String cpf) {
        pessoaFisicaRepository.findByCpf(cpf).ifPresent(
                existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CPF " + cpf + " já existe.");
                }
        );
    }

    private PaisEntity buscarPaisPorId(Long paisId) {
        return paisRepository.findById(paisId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("País não encontrado com ID: " + paisId));
    }

    private OrientacaoSexualEntity buscarOrientacaoSexualPorId(Long orientacaoSexualId) {
        return orientacaoSexualRepository.findById(orientacaoSexualId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Orientação não encontrada com ID: " + orientacaoSexualId));
    }

    private IdentidadeGeneroEntity buscarIdentidadeGeneroPorId(Long identidadeDeGeneroId) {
        return identidadeGeneroRepository.findById(identidadeDeGeneroId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Identidade de gênero não encontrada com ID: " + identidadeDeGeneroId));
    }

    @Override
    public Page<Pessoa> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nome").ascending());
        Page<PessoaEntity> pessoaEntities = pessoaRepository.findAll(pageable);

        List<Pessoa> pessoas = pessoaEntities.getContent().stream()
                .map(entity -> modelMapper.map(entity, Pessoa.class))
                .toList();

        return new PageImpl<>(pessoas, pageable, pessoaEntities.getTotalElements());
    }

    @Override
    public PessoaFisica getByCpf(String cpf) {
        var pessoaEntity = pessoaFisicaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com CPF: " + cpf));
        return modelMapper.map(pessoaEntity, PessoaFisica.class);
    }

    @Override
    public PessoaFisica findById(Long id) {
        var pessoaEntity = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
        return modelMapper.map(pessoaEntity, PessoaFisica.class);
    }

    @Override
    public Page<PessoaFisica> findAllPessoaFisicaPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nomeAbreviado").ascending());
        Page<PessoaFisicaEntity> pessoas = pessoaFisicaRepository.findAll(pageable);

        List<PessoaFisica> pessoaFisicas = pessoas.getContent().stream()
                .map(entity -> modelMapper.map(entity, PessoaFisica.class))
                .toList();

        return new PageImpl<>(pessoaFisicas, pageable, pessoas.getTotalElements());
    }

    @Override
    public Pessoa findPessoaById(Long id) {
        var pessoaEntity = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
        return modelMapper.map(pessoaEntity, Pessoa.class);
    }

    private void validarDuplicidadePessoaJuridica(Pessoa pessoa) {
        pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + " já existe.");
                });
    }

}
