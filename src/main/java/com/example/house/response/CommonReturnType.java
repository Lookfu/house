package com.example.house.response;

import com.example.house.constants.ReturnConstants;

public class CommonReturnType {
    //对应请求的返回结果，有“fail”或“success”
    private String status;
    //若status==success，则data就是需要的JSON格式
    //若status==fail，则date就是通用错误码格式
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, ReturnConstants.SUCCESS);
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setStatus(status);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
