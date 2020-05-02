package com.zukov.allegro.auth.controller;

import com.zukov.allegro.auth.service.AllegroLoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllegroLoginController {

    private final AllegroLoginService allegroLoginService;

    public AllegroLoginController(AllegroLoginService allegroLoginService) {
        this.allegroLoginService = allegroLoginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        String accessToken = allegroLoginService.login();

        if (accessToken == null) {
            throw new IllegalStateException("Access token was not obtained!");
        }

        return accessToken;
    }
}
