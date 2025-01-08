package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.entity.banco.ContaBancariaEntity;
import com.rlti.hexagonal.pessoa.adapters.output.repository.AgenciaRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.ContaBancariaRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaRepository;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Agencia;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.ContaBancaria;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Pessoa;
import com.rlti.hexagonal.pessoa.application.port.output.ContaBancariaOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.rlti.hexagonal.util.Utils.fetchEntity;


@Service
@RequiredArgsConstructor
public class ContaBancariaAdapter implements ContaBancariaOutputPort {

    private final ContaBancariaRepository contaBancariaRepository;
    private final AgenciaRepository agenciaRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public void novaContaBancaria(ContaBancaria contaBancaria) {
        var pessoa = fetchEntity(contaBancaria.getPessoa(),
                Pessoa::getIdPessoa,pessoaRepository::findById,"Pessoa" );

        var agencia = fetchEntity(contaBancaria.getAgencia(),
                Agencia::getIdAgencia,agenciaRepository::findById,"Agência");

        var contaBancariaEntity = new ContaBancariaEntity(contaBancaria, agencia);
        contaBancariaEntity.setPessoa(pessoa);
        contaBancariaRepository.save(contaBancariaEntity);
    }



}
