package com.example.demo.dao.mapper;

import com.example.demo.constant.db.DbConst;
import com.example.demo.dao.bean.UserPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserPO> {


    @Override
    public UserPO mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserPO.builder()
                .id(resultSet.getInt(DbConst.TB_USER_ID))
                .username(resultSet.getString(DbConst.TB_USER_USERNAME))
                .password(resultSet.getString(DbConst.TB_USER_PASSWORD))
                .name(resultSet.getString(DbConst.TB_USER_NAME))
                .email(resultSet.getString(DbConst.TB_USER_EMAIL))
                .gender(resultSet.getString(DbConst.TB_USER_GENDER))
                .telphone(resultSet.getString(DbConst.TB_USER_TELPHONE))
                .build();
    }
}
