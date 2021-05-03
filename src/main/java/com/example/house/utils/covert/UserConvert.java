package com.example.house.utils.covert;

import com.example.house.controller.viewobject.UserVO;
import com.example.house.entity.*;
import com.example.house.service.model.*;
import com.example.house.validate.entity.*;
import org.springframework.beans.BeanUtils;


/**
 * 转换时，如果输入为null，输出也是null
 */
public class UserConvert {

    public static User userCFUserModel(UserModel userModel){
        if(userModel==null){
            return  null;
        }
        User user=new User();
        BeanUtils.copyProperties(userModel,user);
        return  user;
    }

    public static UserModel userModelCFUserVD(UserVD userVD){
        if(userVD==null){
            return null;
        }
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(userVD,userModel);
        return  userModel;
    }

    public static UserModel userModelCFUser(User user){
        if(user==null){
            return null;
        }
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(user,userModel);
        return  userModel;
    }

    public static UserVO userVOCFUserModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }
}
