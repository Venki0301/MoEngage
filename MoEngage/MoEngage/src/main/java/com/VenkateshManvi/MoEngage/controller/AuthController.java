package com.VenkateshManvi.MoEngage.controller;

import com.VenkateshManvi.MoEngage.Model.User;
import com.VenkateshManvi.MoEngage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dogs")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username",username);
        return "home";
    }

    @GetMapping("/signup")
    public String getSignup(){
        return "register";
    }


    @PostMapping("/save")
    public  String saveUser(@ModelAttribute("theUser")User user){

        userService.save(user);

        return  "redirect:/dogs/home";
    }
    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }
}
