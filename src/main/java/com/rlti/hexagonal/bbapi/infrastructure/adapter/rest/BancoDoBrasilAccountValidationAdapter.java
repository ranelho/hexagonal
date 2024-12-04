package com.rlti.hexagonal.bbapi.infrastructure.adapter.rest;

import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationFeignClient;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationService;
import com.rlti.hexagonal.bbapi.domain.model.ValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BancoDoBrasilAccountValidationAdapter implements AccountValidationService {

    private final AccountValidationFeignClient accountValidationFeignClient;

    @Value("${bbapi.validation.gw-dev-app-key}")
    private String gwDevAppKey;

    public BancoDoBrasilAccountValidationAdapter(AccountValidationFeignClient accountValidationFeignClient) {
        this.accountValidationFeignClient = accountValidationFeignClient;
    }

    public ValidationResponse validateAccount(String token, String agencia, String conta, String cpfCnpj) {
        // Gerar o token Bearer
        String bearerToken = "Bearer " + token;

        // Chamada ao Feign Client
        // Chamada ao Feign Client
        Map<String, String> response = accountValidationFeignClient.validateAccount(
                bearerToken, gwDevAppKey, agencia, conta, cpfCnpj);

        // Retornar o objeto DTO com os valores no formato desejado
        return new ValidationResponse(
                "S".equals(response.get("contaAtiva")) ? "S" : "N",
                "S".equals(response.get("titularConta")) ? "S" : "N"
        );
    }
}
