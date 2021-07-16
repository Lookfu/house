package com.example.house.error;

public enum EmBusinessError implements CommonError {

    //10000开头为通用错误类型
    PARAMETER_VALIDATION_ERROR(10000, "参数不合法！"),
    IMAGE_RESOURCE_ERROR(10002, "图片资源不存在！"),
    SYSTEM_BUSY(10003, "系统繁忙！"),
    UNKNOWN_ERROR(10004, "未知错误！"),
    NORMAL_ERROR(10005,"一般错误"),
    //20000开头为用户信息问题
    USER_NOT_EXIST(20000, "用户不存在！"),
    PASSWORD_ERROR(20001, "密码错误！"),
    USER_NOT_LOGIN(20002, "用户未登录或登录超时！"),
    EMAIL_EXIST(20003,"该邮箱已注册！"),
    LINK_OVER_TIME(20004,"链接超时！"),
    USER_DISABLE(20005,"用户未激活！"),
    //30000开头为房子问题
    HOUSE_NOT_EXIST(30000,"该房子不存在"),
    //40000开头为验证问题
    OTPCODE_ERROR(40000, "验证码错误！"),
    EMAIL_ERROR(40001, "邮箱发送失败！"),
    //50000开头为数据库异常
    SQL_EXCEPTION(50000, "数据库异常！"),
    ;
    private int errorCode;
    private String errorMsg;

    EmBusinessError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
