package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.Club;
import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User,Integer> {
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT c FROM User c ORDER BY c.user_id DESC")
    Page<User> findAllUsers(Pageable pageable);
}
