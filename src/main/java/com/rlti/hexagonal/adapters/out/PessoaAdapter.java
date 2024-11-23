package com.rlti.hexagonal.adapters.out;

import com.rlti.hexagonal.adapters.out.repository.PessoaFisicaRepository;
import com.rlti.hexagonal.adapters.out.repository.PessoaJuridicaRepository;
import com.rlti.hexagonal.adapters.out.repository.PessoaRepository;
import com.rlti.hexagonal.adapters.out.repository.entity.PessoaEntity;
import com.rlti.hexagonal.adapters.out.repository.entity.Tipo;
import com.rlti.hexagonal.application.core.domain.Pessoa;
import com.rlti.hexagonal.application.ports.out.PessoaOutputPort;
import com.rlti.hexagonal.handler.DuplicidadeException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaAdapter implements PessoaOutputPort {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    @Override
    @Transactional
    public void salvar(Pessoa pessoa) {
        if (pessoa.getTipo() == Tipo.FISICA) {
            validarDuplicidadePessoaFisica(pessoa);
        } else if (pessoa.getTipo() == Tipo.JURIDICA) {
            validarDuplicidadePessoaJuridica(pessoa);
        }
        var pessoaEntity = new PessoaEntity(pessoa);
        pessoaRepository.save(pessoaEntity);
    }

    private void validarDuplicidadePessoaJuridica(Pessoa pessoa) {
        pessoaJuridicaRepository.findByCnpj(pessoa.getPessoaJuridica().getCnpj())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CNPJ " + pessoa.getPessoaJuridica().getCnpj() + " já existe.");
                });
    }

    private void validarDuplicidadePessoaFisica(Pessoa pessoa) {
        pessoaFisicaRepository.findByCpf(pessoa.getPessoaFisica().getCpf())
                .ifPresent(existingPessoa -> {
                    throw new DuplicidadeException("Pessoa com CPF " + pessoa.getPessoaFisica().getCpf() + " já existe.");
                });
    }
}
