package com.example.quanlysancaulong.service;

import com.example.quanlysancaulong.model.User;
import com.example.quanlysancaulong.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public User save(User user) {
        return loginRepository.save(user);
    }

    @Override
    public User checkAccount(User user) {
        return loginRepository.findByEmailOrPhoneAndPassword(user.getEmail(), user.getPhone(), user.getPassword());
    }
}
