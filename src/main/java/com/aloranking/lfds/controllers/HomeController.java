package com.aloranking.lfds.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.name}")
    private String appName;

    @GetMapping("/")
    public Map getStatus() {
        Map map = new HashMap<String, String>();
        map.put("app-name", appName);
        map.put("app-version", appVersion);

        return map;
    }
}
