package com.fifame.train.common.exception;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author fifame
 * @DATE 2024-08-26 21:29
 * @Version 1.0
 */
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }


    //不写入堆栈信息,提高性能
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

