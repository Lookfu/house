package com.example.house.error;

public enum EmDataAccessError implements CommonError{
    //90000开头为数据库问题
    RESOURCE_FAILURE(90000, "数据访问资源失败！(DataAccessResourceFailureException )"),
    DATE_INTEGRITY(90001, "违反了数据完整性！(DataIntegrityViolationException )"),
    LOCK_FAILURE(90002, "数据库锁问题！(DeadlockLoserDataAccessException )"),
    UPDATE_ERROR(90003, "数据更新问题！(IncorrectUpdateSemanticsDataAccessException )"),
    UNCHECK_ERROR(99999, "未捕获异常！"),
    DATA_DUPLICATE(90004, "数据冗余！（DuplicateKeyException）"),
    ;
    private int errorCode;
    private String errorMsg;

    EmDataAccessError(int errorCode, String errorMsg) {
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
