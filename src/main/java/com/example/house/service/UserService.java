package com.example.house.service;

import com.example.house.service.model.UserModel;

public interface UserService {
    UserModel getUserByEmail(String email);
    int addUser(UserModel userModel);
    String getActivateLink(String random);
    boolean isExistByEmail(String email);
    int enableUser(String email);
}
