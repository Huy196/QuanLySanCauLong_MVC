package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClubService implements IClubService{
    @Autowired
    private ClubRepository clubRepository;
    @Override
    public Page<Club> findAllClub(Pageable pageable) {
        return clubRepository.findAll(pageable);
    }

    @Override
    public Page<Club> findAllClubByName(Pageable pageable, String name) {
        return clubRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public void deleteClub(int id) {
        clubRepository.deleteById(id);
    }
}
