package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    Page<Club> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
