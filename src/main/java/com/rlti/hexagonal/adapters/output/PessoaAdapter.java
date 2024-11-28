package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.*;
import com.rlti.hexagonal.adapters.output.repository.entity.*;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.core.domain.PessoaFisica;
import com.rlti.hexagonal.application.core.domain.PessoaJuridica;
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
    private final ProfissaoRepository profissaoRepository;

    @Override
    @Transactional
    public void salvar(Pessoa pessoa) {
        validarPessoa(pessoa);

        if (pessoa.getTipo() == Tipo.FISICA) {
            verificarDuplicidadeCpf(pessoa.getPessoaFisica().getCpf());
            var pais = buscarPaisPorId(pessoa.getPessoaFisica().getPaisId());
            var orientacao = buscarOrientacaoSexualPorId(pessoa.getPessoaFisica().getOrientacaoSexualId());
            var identidadeGenero = buscarIdentidadeGeneroPorId(pessoa.getPessoaFisica().getIdentidadeDeGeneroId());
            var profissao = buscarProfissaoPorId(pessoa.getPessoaFisica().getProfissaoId());
            var pessoaFisica = new PessoaEntity(pessoa, pais, orientacao, identidadeGenero, profissao);
            pessoaRepository.save(pessoaFisica);
        } else if (pessoa.getTipo() == Tipo.JURIDICA) {
            validarDuplicidadePessoaJuridica(pessoa);
            var pessoaJuridica = new PessoaEntity(pessoa);
            pessoaRepository.save(pessoaJuridica);
        }
    }

    private ProfissaoEntity buscarProfissaoPorId(Long profissaoId) {
        return profissaoRepository.findById(profissaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Profissão não encontrada com ID: " + profissaoId));
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
        if (paisId == null)
            return null;
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("Pessoa.nome").ascending());
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

    @Override
    public PessoaFisica findPessoaFisicaById(Long id) {
        var pessoaFisicaEntity = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
        return modelMapper.map(pessoaFisicaEntity, PessoaFisica.class);
    }

    @Override
    public PessoaJuridica findPessoaJuridicaById(Long idPessoa) {
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa Jurídica não encontrada com ID: " + idPessoa));
        return modelMapper.map(pessoaJuridicaEntity, PessoaJuridica.class);
    }

    @Override
    public Page<PessoaJuridica> findAllPessoaJuridicaPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("Pessoa.nome").ascending());
        Page<PessoaJuridicaEntity> pessoas = pessoaJuridicaRepository.findAll(pageable);

        List<PessoaJuridica> pessoaJuridicas = pessoas.getContent().stream()
                .map(entity -> modelMapper.map(entity, PessoaJuridica.class))
                .toList();
        return new PageImpl<>(pessoaJuridicas, pageable, pessoas.getTotalElements());
    }

    private void validarDuplicidadePessoaJuridica(Pessoa pessoa) {
        pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + " já existe.");
                });
    }

}
