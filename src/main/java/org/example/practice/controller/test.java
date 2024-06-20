package org.example.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class test {
    @RequestMapping("/test")
    @ResponseBody
    public String testJenkins(){
        System.out.println("Testing Jenkins");
        return "Jenkins testing.";
    }
}
