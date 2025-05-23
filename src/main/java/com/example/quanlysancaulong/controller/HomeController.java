package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private ClubService clubService;

    @GetMapping("/")
    public String redirectToLogin(Model model) {

        List<Club> clubs = clubService.findAllClubList();

        model.addAttribute("clubs", clubs);

        Set<String> clubTypes = clubs.stream()
                .map(Club::getType)
                .collect(Collectors.toSet());
        model.addAttribute("clubTypes", clubTypes);


        return "user/home_user";
    }
}
