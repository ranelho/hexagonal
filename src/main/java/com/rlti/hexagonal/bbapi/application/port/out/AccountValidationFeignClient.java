package com.rlti.hexagonal.bbapi.application.port.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "accountValidationClient", url = "${bbapi.validation.base-url}")
public interface AccountValidationFeignClient {

    @GetMapping(value = "/{agencia}-{conta}/situacao", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> validateAccount(
            @RequestHeader("Authorization") String bearerToken,
            @RequestHeader("gw-dev-app-key") String gwDevAppKey,
            @PathVariable("agencia") String agencia,
            @PathVariable("conta") String conta,
            @RequestParam("cpfCnpj") String cpfCnpj
    );
}
