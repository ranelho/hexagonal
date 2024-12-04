package com.rlti.hexagonal.pessoa.adapters.output;

import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;
import com.rlti.hexagonal.pessoa.application.core.domain.Endereco;
import com.rlti.hexagonal.pessoa.application.port.output.BuscaEnderecoOutputPort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscaEnderecoAdapter implements BuscaEnderecoOutputPort {

    private final ViaCepFeignClient viaCepFeignClient;

    @Override
    public EnderecoResponse buscarEndereco(String cep) {
        try {
            EnderecoResponse response = viaCepFeignClient.buscarEndereco(cep);

            if (response == null || response.getCep() == null) {
                throw new IllegalArgumentException("CEP inválido ou não encontrado.");
            }

            return response;
        } catch (FeignException e) {
            throw new IllegalArgumentException("Erro ao consultar ViaCEP: " + e.getMessage(), e);
        }
    }
}
