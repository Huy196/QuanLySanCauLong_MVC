package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends JpaRepository<User, Integer> {
}
