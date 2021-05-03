package com.example.house.error;

import org.springframework.dao.*;


public class DatabaseException {

    public static EmDataAccessError getError(DataAccessException e){
        final Throwable cause = e.getCause();
        EmDataAccessError error;
        if(cause instanceof DataAccessResourceFailureException){
            error=EmDataAccessError.RESOURCE_FAILURE;
        }else if(cause instanceof DataIntegrityViolationException){
            error=EmDataAccessError.DATE_INTEGRITY;
        }else if(cause instanceof DeadlockLoserDataAccessException){
            error=EmDataAccessError.LOCK_FAILURE;
        }else if(cause instanceof IncorrectUpdateSemanticsDataAccessException){
            error=EmDataAccessError.UPDATE_ERROR;
        }else if(cause instanceof DuplicateKeyException){
            error=EmDataAccessError.DATA_DUPLICATE;
        }else{
            error=EmDataAccessError.UNCHECK_ERROR;
        }
        return error;
    }
}
