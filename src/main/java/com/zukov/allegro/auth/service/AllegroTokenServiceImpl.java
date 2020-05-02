package com.zukov.allegro.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

import static java.util.Base64.getEncoder;

public class AllegroTokenServiceImpl {

    private static final String ALLEGRO_HEADER_NAME = "Authorization";

    @Value("${allegro.endpoint.host}")
    protected String host;

    @Value("${allegro.OAuth2.clientId}")
    private String clientId;

    @Value("${allegro.OAuth2.secret}")
    private String secret;

    protected HttpEntity<String> createHttpEntity(HttpHeaders headers) {
        return new HttpEntity<>(null, headers);
    }

    protected HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(ALLEGRO_HEADER_NAME, getAuth(clientId, secret));
        return headers;
    }

    private String getAuth(String restClientId, String restClientSecret) {
        String authValue = restClientId + ":" + restClientSecret;
        return String.format("Basic %s", getEncoder().encodeToString(authValue.getBytes()));
    }
}
