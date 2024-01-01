package com.example.demo.controller.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginParamDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
