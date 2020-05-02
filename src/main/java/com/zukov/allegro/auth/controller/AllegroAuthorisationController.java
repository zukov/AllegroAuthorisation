package com.zukov.allegro.auth.controller;

import com.zukov.allegro.auth.service.AllegroTokenAcquireService;
import com.zukov.allegro.auth.service.AllegroTokenRefreshService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllegroAuthorisationController {

    private final AllegroTokenAcquireService allegroTokenAcquireService;
    private final AllegroTokenRefreshService allegroTokenRefreshService;

    public AllegroAuthorisationController(AllegroTokenAcquireService allegroTokenAcquireService, AllegroTokenRefreshService allegroTokenRefreshService) {
        this.allegroTokenAcquireService = allegroTokenAcquireService;
        this.allegroTokenRefreshService = allegroTokenRefreshService;
    }

    @RequestMapping(value = "/acquireToken", method = RequestMethod.GET)
    public String acquireToken() {
        String accessToken = allegroTokenAcquireService.acquireToken();

        if (accessToken == null) {
            throw new IllegalStateException("Access token was not obtained!");
        }

        return accessToken;
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public String refreshToken() {
        //TODO: implement it!
        throw new NotImplementedException("to be implemented");
        /*String accessToken = allegroTokenRefreshService.refreshToken();

        if (accessToken == null) {
            throw new IllegalStateException("Access token was not obtained!");
        }

        return accessToken;*/
    }
}
