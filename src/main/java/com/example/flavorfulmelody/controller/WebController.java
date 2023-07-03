package com.example.flavorfulmelody.controller;

import com.example.flavorfulmelody.dto.LikeRequestDto;
import com.example.flavorfulmelody.service.LikeHateService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/api/user-like")
    public String getUserLikes(Model model, LikeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("likesUp", LikeHateService.getLikeUp(userDetails.getUser()));

        return "index :: #likesUp";
    }

    @GetMapping("/user-hate")
    public String getUserHates(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("hatesUp", LikeHateService.getHateUp(userDetails.getUser()));

        return "index :: #hatesUp";
    }

    @GetMapping("/postForm")
    public String getPostForm(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("showPostForm", true);
        return "index :: #postForm";
    }

}
