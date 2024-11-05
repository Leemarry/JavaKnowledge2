package com.example.javaknowledge2.demos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;

@RestController
@RequestMapping("/oss1")
public class OssController2 {

    @RequestMapping("/html2")
    public String html() {
        return "index.html";
    }

}
