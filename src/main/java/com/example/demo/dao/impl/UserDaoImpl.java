package com.example.demo.dao.impl;

import com.example.demo.constant.ReturnCode;
import com.example.demo.constant.db.DbConst;
import com.example.demo.constant.produre.UserProdureConst;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.bean.UserPO;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.exception.BooknowException;
import com.example.demo.service.bean.UserBO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.constant.produre.UserProdureConst.*;

@Data
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    @Override
    public List<UserPO> getUsers() {

        List<UserPO> result = null;

        try {
            Map<String, Object> map = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(UserProdureConst.SP_GET_USERS)
                    .withSchemaName(DbConst.SCHEMA_ESBEDU)
                    .withoutProcedureColumnMetaDataAccess()
                    .returningResultSet(UserProdureConst.CURSOR, new UserMapper()).execute();
            result = (List<UserPO>) map.get(UserProdureConst.CURSOR);

        } catch (Exception e) {
            throw new BooknowException(ReturnCode.DB_ERROR);
        }
        if (result.size() < 1) {
            throw new BooknowException(ReturnCode.DATA_NOT_FOUND);
        }


        return result;
    }

    @Override
    public List<UserPO> findBySearch(String param) {

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_PARAM, param);
        List<UserPO> result = null;
        try {
            Map<String, Object> map = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(UserProdureConst.SP_FIND_BY_SEARCH)
                    .withSchemaName(DbConst.SCHEMA_ESBEDU)
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(
                            new SqlParameter(I_PARAM, Types.VARCHAR)
                    )
                    .returningResultSet(UserProdureConst.CURSOR, new UserMapper()).execute(in);
            result = (List<UserPO>) map.get(UserProdureConst.CURSOR);

        } catch (Exception e) {
            throw new BooknowException(ReturnCode.DB_ERROR);
        }


        return result;
    }

    @Override
    public List<UserPO> findByUsername(UserBO bo) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_USERNAME, bo.getUsername());
        List<UserPO> result = null;
        try {
            Map<String, Object> map = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(UserProdureConst.SP_FIND_BY_USERNAME)
                    .withSchemaName(DbConst.SCHEMA_ESBEDU)
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(
                            new SqlParameter(I_USERNAME, Types.VARCHAR)
                    )
                    .returningResultSet(UserProdureConst.CURSOR, new UserMapper()).execute(in);
            result = (List<UserPO>) map.get(UserProdureConst.CURSOR);

        } catch (Exception e) {
            throw new BooknowException(ReturnCode.DB_ERROR);
        }


        return result;
    }

    @Override
    public void save(UserBO bo) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_USERNAME, bo.getUsername())
                .addValue(I_PASSWORD, bo.getPassword())
                .addValue(I_NAME, bo.getName())
                .addValue(I_EMAIL, bo.getEmail())
                .addValue(I_GENDER, bo.getGender())
                .addValue(I_TELPHONE, bo.getTelphone());

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

    @Override
    public void update(UserBO bo) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_ID, bo.getId())
                .addValue(I_USERNAME, bo.getUsername())
                .addValue(I_PASSWORD, bo.getPassword())
                .addValue(I_NAME, bo.getName())
                .addValue(I_EMAIL, bo.getEmail())
                .addValue(I_GENDER, bo.getGender())
                .addValue(I_TELPHONE, bo.getTelphone());

        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.execute(status -> {
            try {
                new SimpleJdbcCall(jdbcTemplate)
                        .withProcedureName(UserProdureConst.SP_UPDATE_USER)
                        .withSchemaName(DbConst.SCHEMA_ESBEDU)
                        .withoutProcedureColumnMetaDataAccess()
                        .declareParameters(
                                new SqlParameter(I_ID, Types.INTEGER),
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

    @Override
    public void delete(Integer id) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_ID, id);

        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.execute(status -> {
            try {
                new SimpleJdbcCall(jdbcTemplate)
                        .withProcedureName(UserProdureConst.SP_DELETE_USER)
                        .withSchemaName(DbConst.SCHEMA_ESBEDU)
                        .withoutProcedureColumnMetaDataAccess()
                        .declareParameters(
                                new SqlParameter(I_ID, Types.INTEGER)
                        ).execute(in);

                status.flush();
            } catch (Exception e) {
                status.setRollbackOnly();
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override
    public Optional<UserPO> findByUsernameAndPassword(UserBO bo) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue(I_USERNAME, bo.getUsername())
                .addValue(I_PASSWORD, bo.getPassword());
        List<UserPO> result = null;
        try {
            Map<String, Object> map = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(UserProdureConst.SP_FIND_BY_USERNAME_AND_PASSWORD)
                    .withSchemaName(DbConst.SCHEMA_ESBEDU)
                    .withoutProcedureColumnMetaDataAccess()
                    .declareParameters(
                            new SqlParameter(I_USERNAME, Types.VARCHAR),
                            new SqlParameter(I_PASSWORD, Types.VARCHAR)
                    )
                    .returningResultSet(UserProdureConst.CURSOR, new UserMapper()).execute(in);
            result = (List<UserPO>) map.get(UserProdureConst.CURSOR);

        } catch (Exception e) {
            throw new BooknowException(ReturnCode.DB_ERROR);
        }

        if (result.size() != 1) {
            throw new BooknowException(ReturnCode.DATA_NOT_FOUND);
        }


        return Optional.ofNullable(result.get(0));

    }


}
