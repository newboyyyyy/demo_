package com.example.demo.service;

import com.example.demo.controller.bean.UserDTO;
import com.example.demo.controller.bean.UserSearchParamDTO;
import com.example.demo.service.bean.UserBO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    List<UserDTO> findBySearch(String param);

    void save(UserBO bo);

    void update(UserBO bo);


    void delete(Integer id);


    UserDTO login(UserBO bo);
}
