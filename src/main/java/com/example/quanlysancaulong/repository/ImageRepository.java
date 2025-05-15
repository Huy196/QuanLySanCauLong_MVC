package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query("SELECT c FROM Image c WHERE c.court.court_id = :courtId")
    List<Image> findAllByCourtId(@Param("courtId") int courtId);
}
