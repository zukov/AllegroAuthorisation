package com.zukov.allegro.auth.service;

import com.zukov.allegro.auth.domain.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static java.util.Base64.getEncoder;

@Service
public class AllegroLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllegroLoginService.class);
    private static final String ALLEGRO_HEADER_NAME = "Authorization";

    @Value("${allegro.endpoint.host}")
    private String host;

    @Value("${allegro.endpoint.suffix}")
    private String suffix;

    @Value("${allegro.OAuth2.clientId}")
    private String clientId;

    @Value("${allegro.OAuth2.secret}")
    private String secret;

    private final RestTemplate restTemplate;

    public AllegroLoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login() {
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = createHttpEntity(headers);

        LOGGER.info("Sending request to allegro security");
        ResponseEntity<AuthResponse> authResponse = restTemplate.exchange(host + suffix, HttpMethod.POST, entity, AuthResponse.class);

        LOGGER.info("Obtained response entity");
        if(authResponse.getBody() != null) {
            String accessToken = authResponse.getBody().getAccessToken();
            LOGGER.info("Successfully obtained access token!");
            return accessToken;
        }

        return null;
    }

    private HttpEntity<String> createHttpEntity(HttpHeaders headers) {
        return new HttpEntity<>(null, headers);
    }

    private HttpHeaders createHeaders() {
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
