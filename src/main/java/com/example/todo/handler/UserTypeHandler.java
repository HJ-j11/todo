package com.example.todo.handler;

import com.example.todo.entity.UserRole;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTypeHandler extends BaseTypeHandler<List<UserRole>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<UserRole> parameter, JdbcType jdbcType) throws SQLException {
        String roles = String.join(",", parameter.stream().map(UserRole::getValue).toArray(String[]::new));
        ps.setString(i, roles);
    }

    @Override
    public List<UserRole> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String roles = rs.getString(columnName);
        if(roles == null || roles.isEmpty())
            return null;
        return convertRolesToList(roles);
    }

    @Override
    public List<UserRole> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String roles = rs.getString(columnIndex);
        if(roles == null || roles.isEmpty())
            return null;
        return convertRolesToList(roles);
    }

    @Override
    public List<UserRole> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String roles = cs.getString(columnIndex);
        if(roles == null || roles.isEmpty())
            return null;
        return convertRolesToList(roles);
    }

    private List<UserRole> convertRolesToList(String roles) {
        List<UserRole> userRoles = new ArrayList<>();
        for (String role : roles.split(",")) {
            userRoles.add(UserRole.valueOf(role.trim().toUpperCase()));
        }
        return userRoles;
    }
}