package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.User;

public interface ILoginService {
    User save(User user);

    User checkAccount(User user);
}
