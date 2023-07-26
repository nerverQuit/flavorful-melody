package com.example.flavorfulmelody.controller;

import com.example.flavorfulmelody.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "index";
    }
    @GetMapping("/api/users/mypage")
    public String mypage() { return "mypage"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/signup")
    public String singup() { return "signup"; }
}
