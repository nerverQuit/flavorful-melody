package com.example.flavorfulmelody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/api/login")
    public String login() { return "login"; }

    @GetMapping("/api/signup")
    public String singup() { return "signup"; }

    @GetMapping("/api/users/mypage")
    public String mypage() { return "mypage"; }

}
