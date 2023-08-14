package com.example.upload.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
//@CrossOrigin(origins="http://localhost:8081")
@GetMapping("/")
    public String home(){
    return "Data 준비 중123...";
}
}
