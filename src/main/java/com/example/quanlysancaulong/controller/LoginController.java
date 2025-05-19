package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.service.ClubService;
import com.example.quanlysancaulong.service.LoginService;
import com.example.quanlysancaulong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private LoginService iLoginService;

    @GetMapping("")
    public String login(Model model, HttpSession session) {
        session.removeAttribute("registration");
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/signIn")
    public String signIn(Model model, HttpSession session) {
        session.removeAttribute("registrationSuccess");
        model.addAttribute("user", new User());
        return "sign_in";
    }

    @PostMapping("saveUser")
    public String save(Model model, @ModelAttribute("user") User user, HttpSession session) {
        user.setRole(1);
        if (user.getImage() == null) {
            user.setImage("default-avatar.jpg");
        }
        iLoginService.save(user);
        session.setAttribute("registrationSuccess", true);
        return "sign_in";
    }

    @PostMapping("account")
    public String loginAccount(Model model, @ModelAttribute("user") User user, HttpSession session) {
        User user1 = iLoginService.checkAccount(user);

        if (user1 != null) {
            session.setAttribute("userId", user1.getUser_id());

            if (user1.getRole() == 1) {
                return "home_user";
            } else if (user1.getRole() == 0) {
                return "admin/home_admin";
            } else if (user1.getRole() == 2) {
                Club club = clubService.findClubByUserId(user1.getUser_id());
                if (club != null) {
                    session.setAttribute("clubId", club.getClub_id());
                    model.addAttribute("user", user1);
                    model.addAttribute("club", club);
                    return "admin/home_club";
                } else {
                    model.addAttribute("error", "Không tìm thấy club cho user này");
                    return "login";
                }
            } else {
                model.addAttribute("error", "Quyền truy cập không hợp lệ");
                return "login";
            }
        }
        session.setAttribute("registration", true);
        return "login";
    }


    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
