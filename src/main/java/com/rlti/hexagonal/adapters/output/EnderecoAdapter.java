package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.EnderecoRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaFisicaRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.EnderecoEntity;
import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.output.EnderecoOutputPort;
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
