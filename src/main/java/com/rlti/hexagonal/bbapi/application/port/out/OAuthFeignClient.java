package com.rlti.hexagonal.bbapi.application.port.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "oauthClient", url = "${bbapi.oauth.token-url}")
public interface OAuthFeignClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Map<String, Object> getToken(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam("grant_type") String grantType,
            @RequestParam("scope") String scope
    );
}
