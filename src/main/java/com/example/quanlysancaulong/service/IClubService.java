package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClubService {
    Page<Club> findAllClub(Pageable pageable);

    Page<Club> findAllClubByName(Pageable pageable,String name);

    void deleteClub(int id);
}
