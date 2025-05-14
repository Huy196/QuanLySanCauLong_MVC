package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourtService implements ICourtService{
    @Autowired
    private CourtRepository courtRepository;
    @Override
    public Page<Court> findAllCourt(Pageable pageable) {
        return courtRepository.findAll(pageable);
    }

    @Override
    public Page<Court> searchNameCourt(String name, Pageable pageable) {
        return courtRepository.findByNameContainingIgnoreCase(name,pageable);
    }
}
