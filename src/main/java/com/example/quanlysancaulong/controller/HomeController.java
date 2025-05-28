package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.model.Image;
import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.service.ClubService;
import com.example.quanlysancaulong.service.ICourtService;
import com.example.quanlysancaulong.service.IImageService;
import com.example.quanlysancaulong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private ClubService clubService;

    @Autowired
    private IImageService iImageService;

    @Autowired
    private ICourtService iCourtService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/")
    public String redirectToLogin(Model model, HttpSession httpSession) {
        Integer user_id = (Integer) httpSession.getAttribute("userId");

        if (user_id != null){
            User user = iUserService.findUserById(user_id);
            model.addAttribute("user",user);
        }

        List<Club> clubs = clubService.findAllClubList();

        model.addAttribute("clubs", clubs);

        Set<String> clubTypes = clubs.stream()
                .map(Club::getType)
                .collect(Collectors.toSet());
        model.addAttribute("clubTypes", clubTypes);


        return "user/home_user";
    }

    @GetMapping("home/detail_club")
    private String showDetailClub(Model model, @RequestParam("club_id") int club_id){
        Club club = clubService.findClubById(club_id);
        User user = iUserService.findUserById(club.getUser().getUser_id());
        List<Court> courts = iCourtService.findBuIdClubCourt(club.getClub_id());

        List<Image> imageUrls = new ArrayList<>();

        for (Court c: courts
             ) {
            List<Image> images = iImageService.findAllImage(c.getCourt_id());
            imageUrls.addAll(images);
        }

        model.addAttribute("club", club);
        model.addAttribute("user", user);
        model.addAttribute("imageUrls", imageUrls);
        return "user/detail_club";
    }
}
