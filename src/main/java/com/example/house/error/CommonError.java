package com.example.house.error;

public interface CommonError {
    int getErrorCode();

    String getErrorMsg();

    CommonError setErrorMsg(String errorMsg);
}
