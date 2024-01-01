package com.example.demo.exception;

import com.example.demo.constant.ReturnCode;
import lombok.Getter;

@Getter
public class BooknowException extends RuntimeException{

    private ReturnCode returnCode;

    public BooknowException(String message){
        super(message);
    }

    public BooknowException(ReturnCode returnCode){
        super(new StringBuilder(returnCode.getCode())
                .append(":")
                .append(returnCode.getValue())
                .toString());
        this.returnCode = returnCode;
    }

    public BooknowException(ReturnCode returnCode,Throwable cause){
        super(cause);
        this.returnCode = returnCode;
    }
}
