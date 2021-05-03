package com.example.house.service.impl;

import com.example.house.mapper.UserMapper;
import com.example.house.service.UserService;
import com.example.house.service.model.UserModel;
import com.example.house.utils.covert.UserConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Value("${domain.name}")
    private String domainName;

    @Override
    public UserModel getUserByEmail(String email) {
        UserModel userModel=UserConvert.userModelCFUser(userMapper.getUserByEmail(email));
        return userModel;
    }

    @Override
    public int addUser(UserModel userModel) {
        //add user
        return userMapper.addUser(UserConvert.userCFUserModel(userModel));
    }

    @Override
    public String getActivateLink(String random) {
        return "http://"+domainName+"/user/verify?key="+random;
    }

    @Override
    public boolean isExistByEmail(String email) {
        if(getUserByEmail(email)!=null){
            return true;
        }
        return false;
    }

    @Override
    public int enableUser(String email) {
        return userMapper.updateEnableByEmail(1,email);
    }
}
