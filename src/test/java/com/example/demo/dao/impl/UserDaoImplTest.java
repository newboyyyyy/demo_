package com.example.demo.dao.impl;

import com.example.demo.constant.ReturnCode;
import com.example.demo.constant.db.DbConst;
import com.example.demo.constant.produre.UserProdureConst;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.exception.BooknowException;
import com.example.demo.service.bean.UserBO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import static com.example.demo.constant.produre.UserProdureConst.*;
import static com.example.demo.constant.produre.UserProdureConst.I_TELPHONE;


@SpringBootTest
class UserDaoImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Test
    void findBySearch() throws SQLException {

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(UserProdureConst.I_PARAM, "陳大");

        Map<String, Object> map = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(UserProdureConst.SP_FIND_BY_SEARCH)
                .withSchemaName(DbConst.SCHEMA_ESBEDU)
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter(UserProdureConst.I_PARAM, Types.VARCHAR)
                )
                .returningResultSet(UserProdureConst.CURSOR, new UserMapper()).execute(in);

        System.out.println(map);

    }

    @Test
    void update(){
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_USERNAME, "simple1")
                .addValue(I_PASSWORD,"password")
                .addValue(I_NAME,"郭富城")
                .addValue(I_EMAIL,"ww252046@gmail.com")
                .addValue(I_GENDER,"男")
                .addValue(I_TELPHONE,"0912123456");

        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.execute(status -> {
            try {
                new SimpleJdbcCall(jdbcTemplate)
                        .withProcedureName(UserProdureConst.SP_ADD_USER)
                        .withSchemaName(DbConst.SCHEMA_ESBEDU)
                        .withoutProcedureColumnMetaDataAccess()
                        .declareParameters(
                                new SqlParameter(I_USERNAME, Types.VARCHAR),
                                new SqlParameter(I_PASSWORD, Types.VARCHAR),
                                new SqlParameter(I_NAME, Types.VARCHAR),
                                new SqlParameter(I_EMAIL, Types.VARCHAR),
                                new SqlParameter(I_GENDER, Types.VARCHAR),
                                new SqlParameter(I_TELPHONE, Types.VARCHAR)
                        ).execute(in);


                status.flush();
            } catch (Exception e) {
                status.setRollbackOnly();
                e.printStackTrace();
            }
            return null;
        });

    }
}