package com.example.demo.controller.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
public class UserParamDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String username;

    private String password;

    @NotNull
    private String name;

    private String email;

    private String gender;

    private String telphone;
}
