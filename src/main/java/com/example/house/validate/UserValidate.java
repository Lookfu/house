package com.example.house.validate;

import com.example.house.entity.User;
import com.example.house.validate.entity.UserVD;
import org.springframework.util.StringUtils;

public class UserValidate {

    public static String singInByEmail(UserVD userVD){
        String errorMsg="";
        if(StringUtils.isEmpty(userVD.getConfirmPasswd())||
                StringUtils.isEmpty(userVD.getEmail())||
                StringUtils.isEmpty(userVD.getPassword())){
            errorMsg="输入不能为空";
        }
        return errorMsg;
    }

    public static String loginInByEmail(UserVD userVD){
        String errorMsg="";
        if(StringUtils.isEmpty(userVD.getPassword())||
                StringUtils.isEmpty(userVD.getEmail())){
            errorMsg="输入不能为空";
        }
        return errorMsg;
    }
}
