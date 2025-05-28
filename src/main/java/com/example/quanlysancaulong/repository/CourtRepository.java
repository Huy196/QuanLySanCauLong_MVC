package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.Court;
import com.example.quanlysancaulong.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Integer> {
    Page<Court> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Court> findAllByClub(Club club, Pageable pageable);

    @Query("SELECT c FROM Court c WHERE c.club.club_id = :clubId")
    List<Court> findAllByClubId(@Param("clubId") Integer clubId);

}
