package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.repository.EnderecoRepository;
import com.rlti.hexagonal.adapters.output.repository.PessoaFisicaRepository;
import com.rlti.hexagonal.adapters.output.repository.entity.EnderecoEntity;
import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.output.EnderecoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoAdapter implements EnderecoOutputPort {

    private final EnderecoRepository enderecoRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    public void salvarEndereco(Long idPessoaFisica, Endereco endereco) {
/*        var pessoaFisica = pessoaFisicaRepository.findById(idPessoaFisica).orElseThrow(
                () -> new RuntimeException("Pessoa física não encontrada")
        );
        var enderecoEntity = new EnderecoEntity(pessoaFisica, endereco);
        enderecoRepository.save(enderecoEntity);*/
    }
}
