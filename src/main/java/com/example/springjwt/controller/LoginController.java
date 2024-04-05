package com.example.springjwt.controller;

import com.example.springjwt.dto.LoginDTO;
import com.example.springjwt.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginDTO loginDTO){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();


        return "/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, Model model, HttpServletResponse response){

        Boolean checkLogin = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());

        if(checkLogin == null){
            return "/loginForm";
        }

        model.addAttribute("loginDTO", loginDTO);

        return "redirect:/home";
    }


}
