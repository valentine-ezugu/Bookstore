package com.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    private static final String VERSION = "1.0";

    @GetMapping(value = "/version")
    public String getVersion() {
        return VERSION;
    }

}
