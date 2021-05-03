package com.example.house.error.handler;

import com.example.house.error.DatabaseException;
import com.example.house.error.EmDataAccessError;
import com.example.house.response.CommonReturnType;
import org.springframework.dao.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class DataAccessExceptionHandler {
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public Object handlerException(DataAccessException e) {
        EmDataAccessError error=DatabaseException.getError(e);
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus("fail");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("errorCode", error.getErrorCode());
        hashMap.put("errorMsg", error.getErrorMsg());
        //把详细错误输出到控制台
        System.out.println(e.getCause());
        e.printStackTrace();
        commonReturnType.setData(hashMap);
        return commonReturnType;
    }
}
