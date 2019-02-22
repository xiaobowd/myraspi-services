package com.xiaobowd.mysite.exception;

public class MyException extends Throwable{

    private String exceptionMessage;

    public MyException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
