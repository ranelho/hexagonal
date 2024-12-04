package com.rlti.hexagonal.pessoa.adapters.output;

import com.rlti.hexagonal.pessoa.adapters.output.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepFeignClient {

    @GetMapping("/{cep}/json")
    EnderecoResponse buscarEndereco(@PathVariable("cep") String cep);
}
