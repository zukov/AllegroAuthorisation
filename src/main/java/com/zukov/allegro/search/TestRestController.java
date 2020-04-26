package com.zukov.allegro.search;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @ApiOperation(value = "Default annotations")
    @GetMapping("/default")
    public RestResourceTest defaultMessage() {
        return new RestResourceTest("default");
    }
}