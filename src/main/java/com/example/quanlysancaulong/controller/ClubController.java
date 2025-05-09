package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.service.IClubService;
import com.example.quanlysancaulong.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

}
