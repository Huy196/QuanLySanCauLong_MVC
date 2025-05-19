package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.model.Image;
import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.service.*;
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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

@Controller
@RequestMapping("court")
public class CourtController {
    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private IImageService iImageService;

    @Autowired
    private ICourtService iCourtService;

    @Autowired
    private IClubService clubService;
    @Autowired
    private IUserService userService;

    @GetMapping("showAllCourt")
    private ModelAndView showAllCourt(@RequestParam(defaultValue = "") String search, @RequestParam("club_id") int club_id, @PageableDefault(6) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("admin/court/list_court");

        Page<Court> courts = null;

        if (!search.isEmpty()) {
            courts = iCourtService.searchNameCourt(search, pageable);
        } else {
            courts = iCourtService.findAllCourt(pageable);
        }

        Club club = clubService.findClubById(club_id);

        List<String> formattedPrices = new ArrayList<>();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        for (Court court : courts) {
            String formattedPrice = currencyFormat.format(court.getPrice());
            formattedPrices.add(formattedPrice);
        }


        modelAndView.addObject("courts", courts);
        modelAndView.addObject("club", club);
        modelAndView.addObject("search", search);
        modelAndView.addObject("formattedPrices", formattedPrices);
        return modelAndView;
    }

    @GetMapping("editCourt")
    private String showFormEditCourt(Model model,
                                     @RequestParam("club_id") int club_id,
                                     @RequestParam("court_id") int court_id) {
        Club club = clubService.findClubById(club_id);
        Court court = iCourtService.findByIdCourt(court_id);
        List<Image> imageUrls = iImageService.findAllImage(court_id);

        model.addAttribute("club", club);
        model.addAttribute("imageUrls", imageUrls);
        model.addAttribute("court", court);

        return "admin/court/edit_court";
    }

    @GetMapping("addCourt")
    private String showFormAddCourt(Model model,
                                    @RequestParam("club_id") int club_id) {
        Club club = clubService.findClubById(club_id);
        Court court = new Court();
        court.setClub(club);

        model.addAttribute("club", club);
        model.addAttribute("imageUrls", new Image());
        model.addAttribute("court", court);

        return "admin/court/add_court";
    }

    @GetMapping("detailCourt")
    private String showFormDetailCourt(Model model,
                                       @RequestParam("club_id") int club_id,
                                       @RequestParam("court_id") int court_id) {
        Club club = clubService.findClubById(club_id);

        Court court = iCourtService.findByIdCourt(court_id);
        List<Image> imageUrls = iImageService.findAllImage(court_id);

        model.addAttribute("club", club);
        model.addAttribute("imageUrls", imageUrls);
        model.addAttribute("court", court);

        return "admin/court/detail_court";
    }

    @PostMapping("updateCourt")
    private String updateCourt(Model model,
                               @ModelAttribute("court") Court court,
                               @RequestParam("imageFile") MultipartFile[] newFiles,
                               @RequestParam(value = "oldImageNames", required = false) String[] image_id,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) throws IOException {
        Integer userId = (Integer) session.getAttribute("userId");
        boolean checkIdUser = court.getCourt_id() == 0;


        if (checkIdUser) {
            User user = userService.findByIdUser(userId);
            court.setUser(user);

            Club club = clubService.findClubById(court.getClub().getClub_id());
            court.setClub(club);
        } else {
            List<Image> images = iImageService.findAllImage(court.getCourt_id());

            Set<String> validIds = new HashSet<>(Arrays.asList(image_id));

            for (Image image : images) {
                String idStr = String.valueOf(image.getImage_id());
                if (!validIds.contains(idStr)) {
                    iImageService.deleteImage(image.getImage_id());
                }
            }

        }
        iCourtService.updateCourt(court);


        for (MultipartFile file : newFiles) {
            if (!file.isEmpty()) {
                String fileName = uploadFileService.uploadFile(file);
                Court court1 = iCourtService.findByIdCourt(court.getCourt_id());
                iImageService.saveImage(fileName, court1);
            }
        }

        if (checkIdUser) {
            redirectAttributes.addFlashAttribute("message", "Thêm mới sân thành công!");
            return "redirect:/court/addCourt?club_id=" + court.getClub().getClub_id();
        } else {
            redirectAttributes.addFlashAttribute("message", "Cập nhật sân thành công!");
            return "redirect:/court/editCourt?club_id=" + court.getClub().getClub_id() + "&court_id=" + court.getCourt_id();
        }
    }
}
