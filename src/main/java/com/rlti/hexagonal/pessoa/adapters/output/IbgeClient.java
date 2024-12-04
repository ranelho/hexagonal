package com.rlti.hexagonal.pessoa.adapters.output;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ibgeClient", url = "https://servicodados.ibge.gov.br/api/v2/cnae")
public interface IbgeClient {

    @GetMapping("/subclasses")
    List<Map<String, Object>> getSubclasses();
}
