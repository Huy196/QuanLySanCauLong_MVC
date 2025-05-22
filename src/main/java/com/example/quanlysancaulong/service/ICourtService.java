package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.model.Image;
import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICourtService {
    Page<Court> findAllCourtByClub(Pageable pageable , Club club);
    Page<Court> findAllCourt(Pageable pageable);

    Page<Court> searchNameCourt(String name,Pageable pageable);

    Court findByIdCourt(int court_id);

    Court updateCourt(Court court);


}
