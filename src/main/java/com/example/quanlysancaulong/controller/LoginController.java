package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService iLoginService;
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    @GetMapping("/signIn")
    public String signIn(Model model,HttpSession session){
        session.removeAttribute("registrationSuccess");
        model.addAttribute("user",new User());
        return "sign_in";
    }

    @PostMapping("saveUser")
    public String save(Model model, @ModelAttribute("user") User user, HttpSession session){
        user.setRole(1);
        iLoginService.save(user);
        session.setAttribute("registrationSuccess", true);
        return "sign_in";
    }
}
