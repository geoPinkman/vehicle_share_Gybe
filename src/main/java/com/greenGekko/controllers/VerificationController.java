package com.greenGekko.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/lk/{uuid}")
public class VerificationController {

    @GetMapping
    public String test(@PathVariable String uuid, Model model) {
        model.addAttribute("user", uuid);
        log.info("lk page by {}", uuid);
        return "/lk";
    }


}
