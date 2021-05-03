package com.example.house.mapper;

import com.example.house.entity.User;
import com.example.house.validate.entity.UserVD;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByEmail(String email);
    int addUser(User user);
    int updateEnableByEmail(int enable,String email);
}
