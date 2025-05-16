package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query("SELECT c FROM Image c WHERE c.court.court_id = :courtId")
    List<Image> findAllByCourtId(@Param("courtId") int courtId);


    @Query("DELETE FROM Image i WHERE i.image_id = :image_id")
    void deleteByCourtId(@Param("image_id") Integer courtId);

}
