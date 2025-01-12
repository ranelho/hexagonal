package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.handler.DuplicidadeException;
import com.rlti.hexagonal.handler.EntidadeException;
import com.rlti.hexagonal.pessoa.adapters.input.request.PessoaFilterParam;
import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.ContaBancariaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.EmailEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.PessoaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoafisica.PessoaFisicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.CnaeEntity;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoajuridica.PessoaJuridicaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.*;
import com.rlti.hexagonal.pessoa.adapters.output.response.PessoaFisicaDTO;
import com.rlti.hexagonal.pessoa.adapters.output.response.PessoaFisicaProjection;
import com.rlti.hexagonal.pessoa.application.core.domain.enums.Sexo;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Email;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pais;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.rlti.hexagonal.util.Utils.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PessoaService implements PessoaOutputPort {

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
    private final ContaBancariaRepository contaBancariaRepository;
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
        var contaBancariaEntity = setContaBancaria(pessoa);
        var pessoaEntity = new PessoaEntity(pessoa, pessoaFisica, pessoaFisica.getPais(), contaBancariaEntity);
        pessoaRepository.save(pessoaEntity);
        return pessoaEntity.getIdPessoa();
    }

    private ContaBancariaEntity setContaBancaria(Pessoa pessoa) {
        var agenciaEntity = findEntity(pessoa.getContaBancaria().getAgencia().getIdAgencia(), agenciaRepository::findById, "Agência");
        return new ContaBancariaEntity(pessoa.getContaBancaria(), agenciaEntity);
    }

    private PessoaFisicaEntity setPessoaFisica(Pessoa pessoa) {
        var pessoaFisica = Objects.requireNonNull(pessoa.getPessoaFisica(), "Pessoa Física não pode ser nula");

        var pais = fetchEntity(pessoaFisica.getPais(),
                Pais::getIdPais, paisRepository::findById, "País");
        var orientacaoSexual = fetchEntity(pessoaFisica.getOrientacaoSexual(),
                OrientacaoSexual::getIdOrientacaoSexual, orientacaoSexualRepository::findById, "Orientação Sexual");
        var identidadeGenero = fetchEntity(pessoaFisica.getIdentidadeGenero(),
                IdentidadeGenero::getId, identidadeGeneroRepository::findById, "Identidade de Gênero");
        var profissao = fetchEntity(pessoaFisica.getProfissao(),
                Profissao::getIdProfissao, profissaoRepository::findById, "Profissão");
        var estadoCivil = fetchEntity(pessoaFisica.getEstadoCivil(),
                EstadoCivil::getIdEstadoCivil, estadoCivilRepository::findById, "Estado Civil");
        var corRaca = fetchEntity(pessoaFisica.getCorRaca(),
                CorRaca::getId, corRacaRepository::findById, "Cor/Raça");
        var grauInstrucao = fetchEntity(pessoaFisica.getGrauInstrucao(),
                GrauInstrucao::getIdGrauInstrucao, grauInstrucaoRepository::findById, "Grau de Instrução");

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
                .orElseThrow(() -> new EntidadeException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa", idPessoa)));
        return modelMapper.map(pessoaEntity, Pessoa.class);
    }

    @Override
    public PessoaFisica findPessoaFisicaById(Long idPessoa) {
        var pessoaFisicaEntity = pessoaFisicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa Fisica", idPessoa)));
        return modelMapper.map(pessoaFisicaEntity, PessoaFisica.class);
    }

    @Override
    public PessoaJuridica findPessoaJuridicaById(Long idPessoa) {
        var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa Juridica", idPessoa)));

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
    public void addEmailPessoa(Long idPessoa, Email email) {
        var pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntidadeException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa", +idPessoa)));
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
                    throw new EntidadeException(format(ENTIDADE_NAO_ENCONTRADA, "Pessoa", +idPessoa));
                }
        );
    }

    // 1 opcao + lenta
    @Override
    public Page<PessoaFisica> listPessoasFisicasWithFilter(Pageable pageable, PessoaFilterParam params) {

        var content = pessoaFisicaRepository.findWithFilters(
                params.getNome(),
                params.getDataFormatada(),
                formatEnum(params.getSexo()),
                params.getCpf(),
                pageable
        );

        List<PessoaFisica> results = content.stream()
                .map(entity -> modelMapper.map(entity, PessoaFisica.class))
                .toList();

        return new PageImpl<>(results, pageable, content.getTotalElements());
    }


    // 2 opcao ++++ rapida
    @Override
    public Page<PessoaFisicaDTO> findWithFilters2(Pageable pageable, PessoaFilterParam filter) {
        Page<PessoaFisicaProjection> result = pessoaFisicaRepository.findWithFilters3(
                filter.getNome(),
                filter.getDataFormatada(),
                formatEnum(filter.getSexo()),
                filter.getCpf(),
                pageable
        );

        List<PessoaFisicaDTO> dtos = result.getContent().stream()
                .map(projection -> new PessoaFisicaDTO(
                        projection.getIdPessoaFisica(),
                        projection.getNome(),
                        projection.getDataNascimento(),
                        Sexo.valueOf(projection.getSexo()),
                        projection.getCpf(),
                        projection.getNacionalidade(),
                        projection.getEmail()
                ))
                .toList();

        return new PageImpl<>(dtos, pageable, result.getTotalElements());
    }

}