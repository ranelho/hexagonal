package com.rlti.hexagonal.bbapi.adapter.out.service;

import com.rlti.hexagonal.bbapi.application.core.domain.ValidateAccount;
import com.rlti.hexagonal.bbapi.application.port.out.AccountValidationFeignClient;
import com.rlti.hexagonal.bbapi.application.port.out.BancoDoBrasilAccountValidationOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BancoDoBrasilAccountValidationServiceAdapter implements BancoDoBrasilAccountValidationOutputPort {

    private final AccountValidationFeignClient accountValidationFeignClient;

    @Value("${bbapi.validation.gw-dev-app-key}")
    private String gwDevAppKey;

    public BancoDoBrasilAccountValidationServiceAdapter(AccountValidationFeignClient accountValidationFeignClient) {
        this.accountValidationFeignClient = accountValidationFeignClient;
    }

    public ValidateAccount validateAccount(String token, String agencia, String conta, String cpfCnpj) {
        String bearerToken = "Bearer " + token;

        Map<String, String> response = accountValidationFeignClient.validateAccount(
                bearerToken, gwDevAppKey, agencia, conta, cpfCnpj);

        return new ValidateAccount(
                "S".equals(response.get("contaAtiva")) ? "S" : "N",
                "S".equals(response.get("titularConta")) ? "S" : "N"
        );
    }
}
