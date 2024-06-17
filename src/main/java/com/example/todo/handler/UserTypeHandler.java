package com.example.todo.handler;

import com.example.todo.entity.UserRole;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeHandler extends BaseTypeHandler<UserRole> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UserRole parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public UserRole getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String role = rs.getString(columnName);
        if(role == null)
            return null;
        return UserRole.valueOf(role.toUpperCase());
    }

    @Override
    public UserRole getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String role = rs.getString(columnIndex);
        if(role == null)
            return null;
        return UserRole.valueOf(role.toUpperCase());
    }

    @Override
    public UserRole getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String role = cs.getString(columnIndex);
        if(role == null)
            return null;
        return UserRole.valueOf(role.toUpperCase());
    }
}