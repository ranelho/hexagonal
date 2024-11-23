package com.rlti.hexagonal.adapters.output;

import com.rlti.hexagonal.adapters.output.response.ViaCepResponse;
import com.rlti.hexagonal.application.core.domain.Endereco;
import com.rlti.hexagonal.application.port.output.ViaCepOutputPort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViaCepFeignAdapter implements ViaCepOutputPort {

    private final ViaCepFeignClient viaCepFeignClient;

    @Override
    public Endereco buscarEndereco(String cep) {
        try {
            ViaCepResponse response = viaCepFeignClient.buscarEndereco(cep);

            if (response == null || response.getCep() == null) {
                throw new IllegalArgumentException("CEP inválido ou não encontrado.");
            }

            var endereco = new Endereco();
            endereco.setCep(response.getCep());
            endereco.setLogradouro(response.getLogradouro());
            endereco.setComplemento(response.getComplemento());
            endereco.setBairro(response.getBairro());
            endereco.setCidade(response.getLocalidade());
            endereco.setUf(response.getUf());
            return endereco;
        } catch (FeignException e) {
            throw new IllegalArgumentException("Erro ao consultar ViaCEP: " + e.getMessage(), e);
        }
    }
}
