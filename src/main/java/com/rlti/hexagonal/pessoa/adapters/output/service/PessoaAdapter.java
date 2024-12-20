package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.PaisEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.EmailEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.IdentidadeGeneroEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.OrientacaoSexualEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.ProfissaoEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.*;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Tipo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.PessoaFisica;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import com.rlti.hexagonal.pessoa.application.port.output.PessoaOutputPort;
import com.rlti.hexagonal.pessoa.handler.DuplicidadeException;
import com.rlti.hexagonal.pessoa.handler.EntidadeNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.LongFunction;

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
    private final EmailRepository emailRepository;
    private final CnaeOutputPort cnaeOutputPort;

    private final PessoaRepositoryCustom pessoaRepositoryCustom;

    @Override
    @Transactional
    public Long salvar(Pessoa pessoa) {
        AtomicReference<Long> idCriado = new AtomicReference<>();

        if (pessoa.getTipo() == Tipo.FISICA) {
            pessoaFisicaRepository.findByCpf(pessoa.getPessoaFisica().getCpf())
                    .ifPresentOrElse(existingPessoa -> {
                        throw new DuplicidadeException("Pessoa com CPF " + pessoa.getPessoaFisica().getCpf() + " já existe.");
                    }, () -> {
                        var pais = findIfExists(pessoa.getPessoaFisica().getPaisId(), this::buscarPaisPorId);
                        var orientacao = findIfExists(pessoa.getPessoaFisica().getOrientacaoSexualId(), this::buscarOrientacaoSexualPorId);
                        var identidadeGenero = findIfExists(pessoa.getPessoaFisica().getIdentidadeDeGeneroId(), this::buscarIdentidadeGeneroPorId);
                        var profissao = findIfExists(pessoa.getPessoaFisica().getProfissaoId(), this::buscarProfissaoPorId);
                        var pessoaFisica = new PessoaEntity(pessoa, pais, orientacao, identidadeGenero, profissao);
                        pessoaRepository.save(pessoaFisica);
                        idCriado.set(pessoaFisica.getIdPessoa());
                    });
        } else if (pessoa.getTipo() == Tipo.JURIDICA) {
            pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
                    .ifPresentOrElse(existingPessoa -> {
                        throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + " já existe.");
                    }, () -> {
                        var pessoaJuridica = new PessoaEntity(pessoa);
                        pessoaRepository.save(pessoaJuridica);
                        cnaeOutputPort.addCnaesV2(pessoaJuridica.getIdPessoa(), pessoa.getPessoaJuridica().getSubclasse(), pessoa.getPessoaJuridica().getCnaes());
                        idCriado.set(pessoaJuridica.getIdPessoa());
                    });
        }
        return idCriado.get();
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
    public PessoaFisica findById(Long idPessoa) {
        var pessoaEntity = pessoaFisicaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + idPessoa));
        return modelMapper.map(pessoaEntity, PessoaFisica.class);
    }

    @Override
    public Page<PessoaFisica> findAllPessoaFisicaPaginated(Pageable pageable) {
        Page<PessoaFisicaEntity> pessoas = pessoaFisicaRepository.findAll(pageable);

        List<PessoaFisica> pessoaFisicas = pessoas.getContent().stream()
                .map(entity -> modelMapper.map(entity, PessoaFisica.class))
                .toList();
        return new PageImpl<>(pessoaFisicas, pageable, pessoas.getTotalElements());
    }

    @Override
    public Pessoa findPessoaById(Long idPessoa) {
        var pessoaEntity = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + idPessoa));
        return modelMapper.map(pessoaEntity, Pessoa.class);
    }

    @Override
    public PessoaFisica findPessoaFisicaById(Long idPessoa) {
        var pessoaFisicaEntity = pessoaFisicaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + idPessoa));
        return modelMapper.map(pessoaFisicaEntity, PessoaFisica.class);
    }

    @Override
    public PessoaJuridica findPessoaJuridicaById(Long idPessoa) {
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa Jurídica não encontrada com ID: " + idPessoa));

        List<Cnae> cnaes = pessoaJuridicaEntity.getCnaes().stream()
                .map(pessoaJuridicaCnae -> {
                    CnaeEntity cnaeEntity = pessoaJuridicaCnae.getCnae();
                    return new Cnae(cnaeEntity.getId(), cnaeEntity.getSubclasse(), cnaeEntity.getDenominacao(), pessoaJuridicaCnae.isPrincipal());
                })
                .toList();
        var pessoaJuridica = modelMapper.map(pessoaJuridicaEntity, PessoaJuridica.class);
        pessoaJuridica.setCnaes(cnaes);

        return pessoaJuridica;
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

    @Override
    public void salvarEmail(Long idPessoa, Email email) {
        var pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada com ID: " + idPessoa));
        var emailEntity = new EmailEntity(email, pessoa);
        emailRepository.save(emailEntity);
    }

    @Override
    public void atualizarPessoa(Long idPessoa, PessoaFisica pessoa) {
/*        var pessoaFisicaEntity = pessoaFisicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pessoa não encontrada com ID: " + idPessoa));*/
        pessoaFisicaRepository.findById(idPessoa).ifPresentOrElse(
                pessoaFisicaEntity -> {
                    pessoaFisicaEntity.update(pessoa);
                    pessoaFisicaRepository.save(pessoaFisicaEntity);
                },
                () -> {
                    throw new EntidadeNaoEncontradaException("Pessoa não encontrada com ID: " + idPessoa);
                }
        );
       /* pessoaFisicaEntity.update(pessoa);
        pessoaFisicaRepository.save(pessoaFisicaEntity);*/
    }

    @Override
    public Page<PessoaFisica> listPessoasFisicasWithFilter(Pageable pageable, PessoaFilterParam params) {

        String dataString = params.getDataNascimento() != null
                ? params.getDataNascimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : null;

        var content = pessoaFisicaRepository.findWithFilters(params.getNome(),
                dataString, params.getSexo(), params.getCpf(), pageable);

        List<PessoaFisica> results = content.stream()
                .map(entity -> modelMapper.map(entity, PessoaFisica.class))
                .toList();

        return new PageImpl<>(results, pageable, content.getTotalElements());

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

  /*  private void verificarDuplicidadeCpf(String cpf) {
        pessoaFisicaRepository.findByCpf(cpf).ifPresent(
                existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CPF " + cpf + " já existe.");
                }
        );
    }*/

    /*private <T> T findIfExists(Long id, Function<Long, T> buscaFunc) {
        return id != null ? buscaFunc.apply(id) : null;
    }*/

    private <T> T findIfExists(Long id, LongFunction<T> buscaFunc) {
        return id != null ? buscaFunc.apply(id) : null;
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

    /*private void validarDuplicidadePessoaJuridica(Pessoa pessoa) {
        pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + " já existe.");
                });
    }*/
}
