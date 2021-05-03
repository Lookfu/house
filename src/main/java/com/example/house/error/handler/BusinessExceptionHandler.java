package com.example.house.error.handler;


import com.example.house.error.BusinessException;
import com.example.house.response.CommonReturnType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class BusinessExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Object handlerException(BusinessException e) {
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setStatus("fail");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("errorCode", e.getErrorCode());
        hashMap.put("errorMsg", e.getErrorMsg());
        commonReturnType.setData(hashMap);
        return commonReturnType;
    }
}
