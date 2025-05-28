package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.model.Image;
import com.example.quanlysancaulong.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService implements ICourtService {
    @Autowired
    private CourtRepository courtRepository;

    @Override
    public Page<Court> findAllCourtByClub(Pageable pageable, Club club) {
        return courtRepository.findAllByClub(club, pageable);
    }

    @Override
    public Page<Court> findAllCourt(Pageable pageable) {
        return courtRepository.findAll(pageable);
    }

    @Override
    public Page<Court> searchNameCourt(String name, Pageable pageable) {
        return courtRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Court findByIdCourt(int court_id) {
        return courtRepository.findById(court_id).orElse(null);
    }

    @Override
    public Court updateCourt(Court court) {
        return courtRepository.save(court);
    }

    @Override
    public List<Court> findBuIdClubCourt(int club_id) {
        return courtRepository.findAllByClubId(club_id);
    }
}
