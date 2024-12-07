package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.repository.EnderecoRepository;
import com.rlti.hexagonal.pessoa.adapters.output.repository.PessoaRepository;
import com.rlti.hexagonal.pessoa.adapters.output.entity.pessoa.EnderecoEntity;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;
import com.rlti.hexagonal.pessoa.application.port.output.EnderecoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoAdapter implements EnderecoOutputPort {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    public void salvarEndereco(Long idPessoa, Endereco endereco) {
        var pessoa = pessoaRepository.findById(idPessoa).orElseThrow(
                () -> new RuntimeException("Pessoa não encontrada")
        );
        var enderecoEntity = new EnderecoEntity(pessoa, endereco);
        enderecoRepository.save(enderecoEntity);
    }
}
