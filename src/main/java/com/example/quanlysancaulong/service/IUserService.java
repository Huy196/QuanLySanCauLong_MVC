package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> findAllUser(Pageable pageable);

    Page<User> findAllUserByName(Pageable pageable,String name);

    void deleteUser(int id);

    User findUserById(int id);

    User saveOrUpdate(User user);

    User findByIdUser(int id);

}
