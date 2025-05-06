package com.example.quanlysancaulong.repository;

import com.example.quanlysancaulong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE (u.email = :email OR u.phone = :phone) AND u.password = :password")
    User findByEmailOrPhoneAndPassword(@Param("email") String email,@Param("phone") String phone, @Param("password") String password);

}
