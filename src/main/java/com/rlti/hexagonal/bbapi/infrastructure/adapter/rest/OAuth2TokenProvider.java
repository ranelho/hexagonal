package com.rlti.hexagonal.bbapi.infrastructure.adapter.rest;


import com.rlti.hexagonal.bbapi.application.port.out.OAuthFeignClient;
import com.rlti.hexagonal.bbapi.application.port.out.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Map;

@Component
public class OAuth2TokenProvider implements TokenProvider {

    private final OAuthFeignClient oauthFeignClient;
    private final String clientId;
    private final String clientSecret;
    private final String scope;

    public OAuth2TokenProvider(
            OAuthFeignClient oauthFeignClient,
            @Value("${bbapi.oauth.client-id}") String clientId,
            @Value("${bbapi.oauth.client-secret}") String clientSecret,
            @Value("${bbapi.oauth.scope}") String scope) {
        this.oauthFeignClient = oauthFeignClient;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    @Override
    public String getOAuthToken() {
        // Geração do cabeçalho Authorization
        String auth = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        String authorizationHeader = "Basic " + auth;

        // Chamada ao Feign Client
        Map<String, Object> response = oauthFeignClient.getToken(authorizationHeader, "client_credentials", scope);
        return (String) response.get("access_token");
    }
}
