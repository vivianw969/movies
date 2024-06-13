package org.example.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.practice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class RedisController {

    @Autowired
    private RedisService redisService;


    @GetMapping("/")
    public String home() {
        return "Hello, World!";
    }

    @PostMapping("/set")
    public String setKey(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "Key set";
    }

    @GetMapping("/get")
    public String getKey(@RequestParam String key) {
        return redisService.getValue(key);
    }

    @GetMapping("/set")
    public String setEndpoint() {
        return "This is the /set endpoint";
    }
}
