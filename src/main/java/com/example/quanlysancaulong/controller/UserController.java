package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.service.IUserService;
import com.example.quanlysancaulong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("getAllUser")
    public ModelAndView showAllUser(@RequestParam(defaultValue = "") String search, @PageableDefault(8) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("admin/list_user");
        Page<User> users;
        if (!search.isEmpty()) {
            users = userService.findAllUserByName(pageable, search);
        } else {
            users = userService.findAllUser(pageable);
        }

        modelAndView.addObject("search", search);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "Xóa người dùng thành công!");

        return "redirect:/user/getAllUser";
    }
}
