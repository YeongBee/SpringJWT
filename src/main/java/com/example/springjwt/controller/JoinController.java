package com.example.springjwt.controller;

import com.example.springjwt.dto.JoinDTO;
import com.example.springjwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinGet(@ModelAttribute JoinDTO joinDTO){
        return "join";
    }


    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO, Model model){
        log.info("joinUsername={}", joinDTO.getUsername());
        model.addAttribute("from", joinDTO);
        joinService.joinProcess(joinDTO);
        return "/home";
    }
}
