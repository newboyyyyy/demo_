package com.example.demo.service.bean;

import com.example.demo.controller.bean.UserDTO;
import com.example.demo.controller.bean.UserLoginParamDTO;
import com.example.demo.controller.bean.UserParamDTO;
import com.example.demo.dao.bean.UserPO;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class UserBO {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String gender;
    private String telphone;

    public static UserBO createBy(UserLoginParamDTO dto){
        return UserBO.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }



    public static UserBO createBy(UserParamDTO dto) {
        if (Objects.isNull(dto.getPassword())) {
            dto.setPassword("123456");
        }
        //  新增使用者
        if (Objects.isNull(dto.getId())) {
            return UserBO.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .gender(dto.getGender())
                    .telphone(dto.getTelphone())
                    .build();
        } else {
            //修改使用者
            return UserBO.builder()
                    .id(dto.getId())
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .gender(dto.getGender())
                    .telphone(dto.getTelphone())
                    .build();
        }


    }

    public static UserBO createBy(UserPO po) {
        return UserBO.builder()
                .id(po.getId())
                .username(po.getUsername())
                .password(po.getPassword())
                .name(po.getName())
                .email(po.getEmail())
                .gender(po.getGender())
                .telphone(po.getTelphone())
                .build();

    }


    public UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .email(this.email)
                .gender(this.gender)
                .telphone(this.telphone)
                .build();
    }

}
