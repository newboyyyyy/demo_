package com.example.demo.service.impl;

import com.example.demo.controller.bean.UserDTO;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.bean.UserPO;
import com.example.demo.exception.BooknowException;
import com.example.demo.service.UserService;
import com.example.demo.service.bean.UserBO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.constant.ReturnCode.*;

@Service
@Setter
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsers() {
        List<UserPO> users = userDao.getUsers();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserPO userPO : users) {
            UserDTO userDTO = UserBO.createBy(userPO).getUserDTO();
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public List<UserDTO> findBySearch(String param) {
        List<UserPO> users;
        if (Objects.isNull(param)) {
            users = userDao.getUsers();
        } else {
            users = userDao.findBySearch(param);
        }


        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserPO userPO : users) {
            UserDTO userDTO = UserBO.createBy(userPO).getUserDTO();
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public void save(UserBO bo) {
        if (StringUtils.isEmpty(bo.getUsername())) {
            throw new BooknowException(NOT_EMPTY);
        }

        List<UserPO> users = userDao.findByUsername(bo);
        if (CollectionUtils.isEmpty(users)) {
            userDao.save(bo);
        } else {
            throw new BooknowException(DATA_REPEAT);
        }


    }

    @Override
    public void update(UserBO bo) {
        userDao.update(bo);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public UserDTO login(UserBO bo) {
        if (StringUtils.isEmpty(bo.getUsername())) {
            throw new BooknowException(NOT_EMPTY);
        }

        if (StringUtils.isEmpty(bo.getPassword())) {
            throw new BooknowException(NOT_EMPTY);
        }

        Optional<UserPO> opt = userDao.findByUsernameAndPassword(bo);
        if(!opt.isPresent()){
            throw new BooknowException(DATA_NOT_FOUND);
        }
        if (Objects.isNull(opt.get())) {
            throw new BooknowException(INPUT_ERROR);
        }

        UserDTO userDTO = UserBO.createBy(opt.get()).getUserDTO();
        return userDTO;
    }
}
