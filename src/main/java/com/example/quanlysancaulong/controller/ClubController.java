package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.service.IClubService;
import com.example.quanlysancaulong.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("club")
public class ClubController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private IClubService iClubService;

    @GetMapping("getAllClub")
    public ModelAndView showAllClub(@RequestParam(defaultValue = "") String search, @PageableDefault(6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("admin/list_club");
        Page<Club> clubs = null;
        if (!search.isEmpty()) {
            clubs = iClubService.findAllClubByName(pageable, search);
        } else {
            clubs = iClubService.findAllClub(pageable);
        }

        modelAndView.addObject("search", search);
        modelAndView.addObject("clubs", clubs);
        return modelAndView;
    }


    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        iClubService.deleteClub(id);
        redirectAttributes.addFlashAttribute("message", "Xóa câu lạc bộ thành công!");

        return "redirect:/club/getAllClub";
    }

    @GetMapping("editClub")
    public String showFromEditUser(Model model, @RequestParam("id") int id) {
        Club club = iClubService.findClubById(id);
        model.addAttribute("club", club);
        return "admin/edit_club";
    }

    @PostMapping("updateClub")
    private String updateClub(@ModelAttribute("club") Club club,
                              @RequestParam(value = "avatarFile", required = false) MultipartFile avatar,
                              @RequestParam(value = "coverFile", required = false) MultipartFile cover,
                              @RequestParam(value = "linkFile") MultipartFile linkFile,
                              Model model,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request) throws IOException {
        boolean isUpdate = (club.getClub_id() != 0);
        club.setCreate_at(LocalDateTime.now());

        if (avatar != null && !avatar.isEmpty()) {
            String fileAvatar = uploadFileService.uploadFile(avatar, request);
            club.setImage(fileAvatar);
        } else {
            if (isUpdate) {
                Club club1 = iClubService.findClubById(club.getClub_id());
                club.setImage(club1.getImage());
            } else {
                club.setImage("default-avatar.jpg");
            }
        }

        if (cover != null && !cover.isEmpty()) {
            String fileCover = uploadFileService.uploadFile(cover, request);
            club.setCover_image(fileCover);

        } else {
            if (isUpdate) {
                Club club1 = iClubService.findClubById(club.getClub_id());
                club.setCover_image(club1.getCover_image());
            } else {
                club.setImage("default-avatar.jpg");
            }
        }

        if (!linkFile.isEmpty()) {
            String fileName = uploadFileService.uploadFile(linkFile, request);
            club.setLink_file(fileName);
        }

        Club club1 = iClubService.saveOrUpdate(club);
        model.addAttribute("club", club1);

        if (isUpdate) {
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công!");
            return "redirect:/club/editClub?id=" + club1.getClub_id();
        } else {
            redirectAttributes.addFlashAttribute("message", "Thêm câu lạc bộ thành công!");
            return "redirect:/club/addUser";
        }
    }
}
