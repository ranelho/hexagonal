package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.AgenciaRepository;
import com.rlti.hexagonal.adapters.output.repository.ContaBancariaRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.ContaBancariaEntity;
import com.rlti.hexagonal.application.core.domain.ContaBancaria;
import com.rlti.hexagonal.application.port.output.ContaBancariaOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaBancariaAdapter implements ContaBancariaOutputPort {

    private final ContaBancariaRepository contaBancariaRepository;
    private final AgenciaRepository agenciaRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    public void novaContaBancaria(Long idPessoa, ContaBancaria contaBancaria) {
        var pessoa = pessoaRepository.findById(idPessoa).orElseThrow(
                () -> new RuntimeException("Pessoa não encontrada")
        );
        var agencia = agenciaRepository.findById(contaBancaria.getIdAgencia()).orElseThrow(
                () -> new RuntimeException("Agência não encontrada")
        );
        var conta = new ContaBancariaEntity(pessoa, contaBancaria, agencia);
    }
}
