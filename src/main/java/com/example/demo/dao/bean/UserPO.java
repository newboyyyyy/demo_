package com.example.demo.dao.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String gender;
    private String telphone;
}
