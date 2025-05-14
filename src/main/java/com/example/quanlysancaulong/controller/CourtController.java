package com.example.quanlysancaulong.controller;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.service.IClubService;
import com.example.quanlysancaulong.service.ICourtService;
import com.example.quanlysancaulong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("court")
public class CourtController {
    @Autowired
    private ICourtService iCourtService;

    @Autowired
    private IClubService clubService;

    @GetMapping("showAllCourt")
    private ModelAndView showAllCourt(@RequestParam(defaultValue = "") String search,@RequestParam("club_id") int club_id, @PageableDefault(6) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("admin/club/list_court");

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
        modelAndView.addObject("courts",courts);
        modelAndView.addObject("club",club);
        modelAndView.addObject("search",search);
        modelAndView.addObject("formattedPrices", formattedPrices);
        return modelAndView;
    }

}
