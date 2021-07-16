package com.example.house.controller;

import com.example.house.constants.ReturnConstants;
import com.example.house.constants.SessionConstants;
import com.example.house.constants.UserConstants;
import com.example.house.controller.viewobject.UserVO;
import com.example.house.error.BusinessException;
import com.example.house.error.EmBusinessError;
import com.example.house.response.CommonReturnType;
import com.example.house.service.MailService;
import com.example.house.service.RedisService;
import com.example.house.service.UserService;
import com.example.house.service.model.UserModel;
import com.example.house.utils.HashUtils;
import com.example.house.utils.RandomUtils;
import com.example.house.utils.covert.UserConvert;
import com.example.house.validate.UserValidate;
import com.example.house.validate.entity.UserVD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisService redisService;

    /**
     * 注册提交:1.注册验证 2 发送邮件
     */
    @PostMapping("/signIn")
    public CommonReturnType userRegister(UserVD userVD) throws BusinessException {
        System.out.println(userVD.getEmail());
        // 用户参数验证
        String errorMsg=UserValidate.singInByEmail(userVD);
        if(!StringUtils.isEmpty(errorMsg)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,errorMsg);
        }
        //邮箱是否已注册
        if(userService.isExistByEmail(userVD.getEmail())){
            throw new BusinessException(EmBusinessError.EMAIL_EXIST);
        }
        UserModel userModel=UserConvert.userModelCFUserVD(userVD);
        //对密码加密
        try {
            userModel.setPassword(HashUtils.EncodeByMd5(userVD.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR);
        }
        int code=RandomUtils.getFiveBitRandomNumber();
        try {
            mailService.sendActivateLinkMail(userVD.getEmail(),
                    userService.getActivateLink(String.valueOf(code)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.EMAIL_ERROR);
        }
        //激活码与邮箱进行绑定在redis中，如果出现相同的code会出现覆盖的情形
        redisService.setString(String.valueOf(code),userVD.getEmail(),5);
        //添加进数据库
        userService.addUser(userModel);
        return CommonReturnType.create("已发送激活链接!");
    }


    
    @GetMapping("/verify")
    public CommonReturnType verify(String key) throws BusinessException {
        String email=redisService.getString(key);
        if(email==null){
            throw new BusinessException(EmBusinessError.LINK_OVER_TIME);
        }
        if(userService.enableUser(email)!=0){
            redisService.deleteValue(key);
            return CommonReturnType.create("激活成功！");
        }
        throw new BusinessException( EmBusinessError.NORMAL_ERROR.setErrorMsg("激活失败"));
    }


    /**************************登录*****************************/

    @PostMapping("/loginIn/email")
    public CommonReturnType signInByEmail(UserVD userVD, HttpServletRequest request) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        String errorMsg=UserValidate.loginInByEmail(userVD);
        if(!StringUtils.isEmpty(errorMsg)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,errorMsg);
        }
        UserModel userModel=userService.getUserByEmail(userVD.getEmail());
        //是否存在
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //是否已激活
        if(userModel.getEnable()== UserConstants.DISABLE){
            throw new BusinessException(EmBusinessError.USER_DISABLE);
        }
        //验证密码
        if(!HashUtils.EncodeByMd5(userVD.getPassword()).equals(userModel.getPassword())){
            throw new BusinessException(EmBusinessError.PASSWORD_ERROR);
        }
        request.getSession(true).setAttribute(SessionConstants.USER, userModel);
        UserVO userVo=UserConvert.userVOCFUserModel(userModel);
        return CommonReturnType.create(userVo);
    }

    /***************************个人信息******************************/

    @GetMapping("/inf")
    public CommonReturnType getInf(HttpServletRequest request){
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        return CommonReturnType.create(UserConvert.userVOCFUserModel(userModel));
    }

}
