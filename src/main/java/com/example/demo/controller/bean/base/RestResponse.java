package com.example.demo.controller.bean.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class RestResponse<Body> implements Serializable {
    private static final long serialVersionUID = -650562263359301794L;
    private ResponseHeader header;
    private Body body;

    @Data
    public static class ResponseHeader implements Serializable{
        private String resultCode;
        private String resultDescription;
    }

}