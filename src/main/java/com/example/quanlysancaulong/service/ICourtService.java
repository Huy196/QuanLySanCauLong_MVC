package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICourtService {
    Page<Court> findAllCourt(Pageable pageable);

    Page<Court> searchNameCourt(String name,Pageable pageable);

    Court findByIdCourt(int court_id);

}
