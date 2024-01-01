package com.example.demo.dao;

import com.example.demo.controller.bean.UserDTO;
import com.example.demo.dao.bean.UserPO;
import com.example.demo.service.bean.UserBO;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserPO> getUsers();

    List<UserPO> findBySearch(String code);

    List<UserPO> findByUsername(UserBO bo);

    void save(UserBO bo);

    void update(UserBO bo);

    void delete(Integer id);


    Optional<UserPO> findByUsernameAndPassword(UserBO bo);
}
