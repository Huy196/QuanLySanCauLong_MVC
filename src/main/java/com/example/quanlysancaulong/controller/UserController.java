package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.service.IUserService;
import com.example.quanlysancaulong.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private IUserService userService;

    @GetMapping("getAllUser")
    public ModelAndView showAllUser(@RequestParam(defaultValue = "") String search, @PageableDefault(6) Pageable pageable) {
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
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "Xóa người dùng thành công!");

        return "redirect:/user/getAllUser";
    }

    @GetMapping("editUser")
    public String showInterfaceEditUser(Model model, @RequestParam("id") Integer id) {
        User user1 = userService.findUserById(id);
        model.addAttribute("user", user1);
        return "admin/edit_user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "imageFile", required = false) MultipartFile image,
                             Model model,
                             RedirectAttributes redirectAttributes) throws IOException {

        boolean isUpdate = (user.getUser_id() != 0);

        if (image != null && !image.isEmpty()) {
            String fileName = uploadFileService.uploadFile(image);
            user.setImage(fileName);
        }else {
            if (isUpdate) {
                User user1 = userService.findByIdUser(user.getUser_id());
                user.setImage(user1.getImage());
            }else {
                user.setImage("default-avatar.jpg");
            }
        }

        if (!isUpdate){
            user.setRole(1);
        }

        User user1 = userService.saveOrUpdate(user);
        model.addAttribute("user", user1);

        if (isUpdate) {
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
            return "redirect:/user/editUser?id=" + user1.getUser_id();
        }else {
            redirectAttributes.addFlashAttribute("message", "Thêm người dùng mới thành công!");
            return "redirect:/user/addUser";
        }
    }

    @GetMapping("addUser")
    public String showInterfaceAddUser(Model model){
        User user = new User();
        user.setImage("default-avatar.jpg");
        model.addAttribute("user",user);
        return "admin/add_user";
    }
}
