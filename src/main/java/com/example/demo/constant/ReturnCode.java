package com.example.demo.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCode {

    SUCCESS("0000", "Success"),
    DB_ERROR("9999", "DB ERROR"),
    RESPONSE_NOT_FOUND("9998", "RESPONSE NOT FOUND"),
    DATA_NOT_FOUND("9997", "DATA NOT FOUND"),
    DATA_REPEAT("9996","DATA_REPEAT - 帳號已被使用過"),
    FAIL("9995","SYSTEM FAIL"),
    NOT_EMPTY("9994","NOT EMPTY"),
    INPUT_ERROR("9993","INPUT ERROR");

    private String code;
    private String value;

    ReturnCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static ReturnCode getById(String code) {
        return Arrays.stream(ReturnCode.values())
                .filter(returnCode -> returnCode.code.equals(code))
                .findAny()
                .get();

    }
}
