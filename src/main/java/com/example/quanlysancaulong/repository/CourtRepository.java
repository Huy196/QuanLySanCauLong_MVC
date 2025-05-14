package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Integer> {
    Page<Court> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
