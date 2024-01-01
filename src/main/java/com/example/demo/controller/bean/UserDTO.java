package com.example.demo.controller.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String gender;

    private String telphone;
}
