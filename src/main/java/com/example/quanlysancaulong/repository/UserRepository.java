package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
