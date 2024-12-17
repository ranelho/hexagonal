package com.rlti.hexagonal.pessoa.adapters.output;

import com.rlti.hexagonal.pessoa.adapters.output.response.MunicipioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ibgeClient", url = "https://servicodados.ibge.gov.br/api")
public interface IbgeClient {

    @GetMapping("/v2/cnae/subclasses")
    List<Map<String, Object>> getSubclasses();

    @GetMapping("/v1/localidades/estados/{uf}/municipios")
    List<MunicipioResponse> getMunicipiosPorUF(@PathVariable("uf") String uf);
}
