package com.czxy.service;

import com.czxy.domain.User;

import java.util.List;

public interface UserService {

    public User login(User user);

    public Boolean register(User user);

    public Boolean findUserByPhone(String phone);


    public Boolean findUserByEmail(String email);

    public List<User> findUserBySex();
}
