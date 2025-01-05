package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.handler.DuplicidadeException;
import com.rlti.hexagonal.handler.EntidadeNaoEncontradaException;
import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.ContaBancariaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.EmailEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.*;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.*;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoafisica.*;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.Cnae;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoajuridica.PessoaJuridica;
import com.rlti.hexagonal.pessoa.application.port.output.CnaeOutputPort;
import com.rlti.hexagonal.pessoa.application.port.output.PessoaOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;

import static com.rlti.hexagonal.util.Utils.formatDataNascimento;
import static com.rlti.hexagonal.util.Utils.formatEnum;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PessoaAdapter implements PessoaOutputPort {

    private static final String ENTIDADE_NAO_ENCONTRADA = "%s não encontrada com ID: %s";

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PaisRepository paisRepository;
    private final OrientacaoSexualRepository orientacaoSexualRepository;
    private final IdentidadeGeneroRepository identidadeGeneroRepository;
    private final ProfissaoRepository profissaoRepository;
    private final EmailRepository emailRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final CorRacaRepository corRacaRepository;
    private final GrauInstrucaoRepository grauInstrucaoRepository;
    private final AgenciaRepository agenciaRepository;
    private final CnaeOutputPort cnaeOutputPort;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public Long criarPessoaFisica(Pessoa pessoa) {
        pessoaFisicaRepository.findByCpf(pessoa.getPessoaFisica().getCpf())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CPF " + pessoa.getPessoaFisica().getCpf() + "já possui cadastro");
                });

        var pessoaFisica = setPessoaFisica(pessoa);
        var contaBancariaEntity = buildContaBancaria(pessoa);
        var pessoaEntity = new PessoaEntity(pessoa, pessoaFisica, pessoaFisica.getPais(), contaBancariaEntity);
        pessoaRepository.save(pessoaEntity);
        return pessoaEntity.getIdPessoa();
    }

    private ContaBancariaEntity buildContaBancaria(Pessoa pessoa) {
        var idAgencia = Optional.ofNullable(pessoa.getContaBancaria())
                .map(ContaBancaria::getAgencia)
                .map(Agencia::getIdAgencia)
                .orElseThrow(() -> new IllegalArgumentException("Conta bancária ou agência não fornecida."));

        var agenciaEntity = findEntity(idAgencia, agenciaRepository::findById, "Agência");

        return new ContaBancariaEntity(pessoa.getContaBancaria(), agenciaEntity);
    }

    private PessoaFisicaEntity setPessoaFisica(Pessoa pessoa) {
        var pessoaFisica = pessoa.getPessoaFisica();

        var pais = Optional.ofNullable(pessoaFisica.getPais())
                .map(Pais::getIdPais)
                .map(id -> findEntity(id, paisRepository::findById, "País"))
                .orElseThrow(() -> new IllegalArgumentException("Pais não encontrado"));

        var orientacaoSexual = Optional.ofNullable(pessoaFisica.getOrientacaoSexual())
                .map(OrientacaoSexual::getIdOrientacaoSexual)
                .map(id -> findEntity(id, orientacaoSexualRepository::findById, "Orientação Sexual"))
                .orElseThrow(() -> new IllegalArgumentException("Orientação Sexual não encontrada"));

        var identidadeGenero = Optional.ofNullable(pessoaFisica.getIdentidadeGenero())
                .map(IdentidadeGenero::getId)
                .map(id -> findEntity(id, identidadeGeneroRepository::findById, "Identidade de Gênero"))
                .orElseThrow(() -> new IllegalArgumentException("Identidade de Gênero não encontrada"));

        var profissao = Optional.ofNullable(pessoaFisica.getProfissao())
                .map(Profissao::getIdProfissao)
                .map(id -> findEntity(id, profissaoRepository::findById, "Profissão"))
                .orElseThrow(() -> new IllegalArgumentException("Profissão não encontrada"));

        var estadoCivil = Optional.ofNullable(pessoaFisica.getEstadoCivil())
                .map(EstadoCivil::getIdEstadoCivil)
                .map(id -> findEntity(id, estadoCivilRepository::findById, "Estado Civil"))
                .orElseThrow(() -> new IllegalArgumentException("Estado Civil não encontrado"));

        var corRaca = Optional.ofNullable(pessoaFisica.getCorRaca())
                .map(CorRaca::getId)
                .map(id -> findEntity(id, corRacaRepository::findById, "Cor/Raça"))
                .orElseThrow(() -> new IllegalArgumentException("Cor/Raça não encontrada"));

        var grauInstrucao = Optional.ofNullable(pessoaFisica.getGrauInstrucao())
                .map(GrauInstrucao::getIdGrauInstrucao)
                .map(id -> findEntity(id, grauInstrucaoRepository::findById, "Grau de Instrução"))
                .orElseThrow(() -> new IllegalArgumentException("Grau de Instrução não encontrado"));

        return new PessoaFisicaEntity(pessoaFisica)
                .toBuilder()
                .pais(pais)
                .orientacaoSexual(orientacaoSexual)
                .identidadeGenero(identidadeGenero)
                .profissao(profissao)
                .estadoCivil(estadoCivil)
                .corRaca(corRaca)
                .grauInstrucao(grauInstrucao)
                .build();
    }

    private <T> T findEntity(long id, LongFunction<Optional<T>> repository, String entidadeNome) {
        return repository.apply(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(entidadeNome + " não encontrada com ID: " + id));
    }


//    @Transactional
//    public Long salvar(Pessoa pessoa) {
//        AtomicReference<Long> idCriado = new AtomicReference<>();
//
//        if (pessoa.getTipo() == Tipo.FISICA) {
//            pessoaFisicaRepository.findByCpf(pessoa.getPessoaFisica().getCpf())
//                    .ifPresentOrElse(existingPessoa -> {
//                        throw new DuplicidadeException("Pessoa com CPF " + pessoa.getPessoaFisica().getCpf() + JA_EXISTE);
//                    }, () -> {
//                        var pais = findEntityById(
//                                pessoa.getPessoaFisica().getPais().getIdPais(),
//                                paisRepository::findById,
//                                () -> new EntidadeNaoEncontradaException("País não encontrado com ID: " + pessoa.getPessoaFisica().getPais().getIdPais())
//                        );
//                        var orientacao = findEntityById(
//                                pessoa.getPessoaFisica().getOrientacaoSexual().getIdOrientacaoSexual(),
//                                orientacaoSexualRepository::findById,
//                                () -> new EntidadeNaoEncontradaException("Orientação não encontrada com ID: " + pessoa.getPessoaFisica().getOrientacaoSexual().getIdOrientacaoSexual())
//                        );
//                        var identidadeGenero = findEntityById(
//                                pessoa.getPessoaFisica().getIdentidadeGenero().getId(),
//                                identidadeGeneroRepository::findById,
//                                () -> new EntidadeNaoEncontradaException("Identidade de gênero não encontrada com ID: " + pessoa.getPessoaFisica().getIdentidadeGenero().getId())
//                        );
//                        var profissao = findEntityById(
//                                pessoa.getPessoaFisica().getProfissao().getIdProfissao(),
//                                profissaoRepository::findById,
//                                () -> new EntidadeNaoEncontradaException("Profissão não encontrada com código: " + pessoa.getPessoaFisica().getProfissao().getIdProfissao())
//                        );
//                        var estadoCivil = findEntityById(
//                                pessoa.getPessoaFisica().getEstadoCivil().getIdEstadoCivil(),
//                                estadoCivilRepository::findById,
//                                () -> new EntidadeNaoEncontradaException("Estado Civil não encontrado com ID: " + pessoa.getPessoaFisica().getEstadoCivil().getIdEstadoCivil())
//                        );
//                        var pessoaFisica = new PessoaEntity(pessoa, pais, orientacao, identidadeGenero, profissao, estadoCivil);
//                        pessoaRepository.save(pessoaFisica);
//                        idCriado.set(pessoaFisica.getIdPessoa());
//                    });
//        } else if (pessoa.getTipo() == Tipo.JURIDICA) {
//          /*  pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
//                    .ifPresentOrElse(existingPessoa     -> {
//                        throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + JA_EXISTE);
//                    }, () -> {
//                        var pais = findEntityById(
//                                pessoa.getPessoaFisica().getPais().getIdPais(),
//                                paisRepository::findById,
//                                () -> new EntidadeNaoEncontradaException("País não encontrado com ID: " + pessoa.getPessoaFisica().getPais().getIdPais())
//                        );
//                        var pessoaJuridica = new PessoaEntity(pessoa,  pais);
//                        pessoaRepository.save(pessoaJuridica);
//                        cnaeOutputPort.addCnaesV2(pessoaJuridica.getIdPessoa(), pessoa.getPessoaJuridica().getSubclasse(), pessoa.getPessoaJuridica().getCnaes());
//                        idCriado.set(pessoaJuridica.getIdPessoa());
//                    });*/
//        }
//        return idCriado.get();
//    }

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
                .orElseThrow(() -> new RuntimeException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa Fisica", +idPessoa)));
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
                .orElseThrow(() -> new EntidadeNaoEncontradaException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa", +idPessoa)));
        return modelMapper.map(pessoaEntity, Pessoa.class);
    }

    @Override
    public PessoaFisica findPessoaFisicaById(Long idPessoa) {
        var pessoaFisicaEntity = pessoaFisicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa Fisica", +idPessoa)));
        return modelMapper.map(pessoaFisicaEntity, PessoaFisica.class);
    }

    @Override
    public PessoaJuridica findPessoaJuridicaById(Long idPessoa) {
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa Juridica", +idPessoa)));

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
                .orElseThrow(() -> new EntidadeNaoEncontradaException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa", +idPessoa)));
        var emailEntity = new EmailEntity(email, pessoa);
        emailRepository.save(emailEntity);
    }

    @Override
    public void atualizarPessoa(Long idPessoa, PessoaFisica pessoa) {
        pessoaFisicaRepository.findById(idPessoa).ifPresentOrElse(
                pessoaFisicaEntity -> {
                    pessoaFisicaEntity.update(pessoa);
                    pessoaFisicaRepository.save(pessoaFisicaEntity);
                },
                () -> {
                    throw new EntidadeNaoEncontradaException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa", +idPessoa));
                }
        );
    }

    @Override
    public Page<PessoaFisica> listPessoasFisicasWithFilter(Pageable pageable, PessoaFilterParam params) {

        var formattedDataNascimento = formatDataNascimento(params.getDataNascimento());
        var sexoString = formatEnum(params.getSexo());

        var content = pessoaFisicaRepository.findWithFilters(
                params.getNome(),
                formattedDataNascimento,
                sexoString,
                params.getCpf(),
                pageable
        );

        List<PessoaFisica> results = content.stream()
                .map(entity -> modelMapper.map(entity, PessoaFisica.class))
                .toList();

        return new PageImpl<>(results, pageable, content.getTotalElements());
    }


}
