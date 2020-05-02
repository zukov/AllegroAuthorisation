package com.zukov.allegro.auth.service;

import com.zukov.allegro.auth.domain.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AllegroTokenAcquireServiceImpl extends AllegroTokenServiceImpl implements AllegroTokenAcquireService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllegroTokenAcquireServiceImpl.class);

    @Value("${allegro.endpoint.acquireToken.suffix}")
    private String suffix;

    private final RestTemplate restTemplate;

    public AllegroTokenAcquireServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String acquireToken() {
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

}
