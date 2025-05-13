package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    Page<Club> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT c FROM Club c where c.status = 'Hoạt động' ORDER BY c.club_id DESC ")
    Page<Club> findAllClubs(Pageable pageable);

    @Query("SELECT c FROM Club c where c.status = 'Chờ duyệt' ORDER BY c.club_id DESC ")
    Page<Club> findAllNewClubs(Pageable pageable);

}
