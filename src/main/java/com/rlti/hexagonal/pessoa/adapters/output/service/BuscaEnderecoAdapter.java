package com.rlti.hexagonal.pessoa.adapters.output.service;

import com.rlti.hexagonal.pessoa.adapters.output.ViaCepFeignClient;
import com.rlti.hexagonal.pessoa.application.core.domain.pessoa.Endereco;
import com.rlti.hexagonal.pessoa.application.port.output.BuscaEnderecoOutputPort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscaEnderecoAdapter implements BuscaEnderecoOutputPort {

    private final ViaCepFeignClient viaCepFeignClient;

    @Override
    public Endereco buscarEndereco(String cep) {
        try {
            Endereco response = viaCepFeignClient.buscarEndereco(cep);
            if (response == null || response.getCep() == null) {
                throw new IllegalArgumentException("CEP inválido ou não encontrado.");
            }
            return response;
        } catch (FeignException e) {
            throw new IllegalArgumentException("Erro ao consultar ViaCEP: " + e.getMessage(), e);
        }
    }
}
