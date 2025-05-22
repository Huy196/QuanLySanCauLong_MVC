package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClubService {
    Page<Club> findAllClub(Pageable pageable);
    Page<Club> findAllNewClub(Pageable pageable);

    Page<Club> findAllClubByName(Pageable pageable,String name);

    void deleteClub(int id);

    Club findClubById(int id);

    Club saveOrUpdate(Club club);

    Club findClubByUserId(int id);
    List<Club> findAllClubList();
}
