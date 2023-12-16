package com.greenGekko.controllers;

import com.greenGekko.dto.UserRegistrationDto;
//import com.greenGekko.servicies.UserService;
import com.greenGekko.exceptions.ResourceException;
import com.greenGekko.models.JUser;
import com.greenGekko.servicies.user_service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UsersService usersService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model){
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") UserRegistrationDto user) {
        if (user.getUserPassword().equals(user.getConfirmPassword())) {
            try {
                JUser jUser = usersService.createUser(user);
                return "redirect:/registration?success";
            }
            catch (ResourceException e) {
                log.warn("Error {}", e.getMessage());
                return "redirect:/registration?emailError";
            }
        }
        return "redirect:/registration?passwordError";

    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping(value = {"/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

}
