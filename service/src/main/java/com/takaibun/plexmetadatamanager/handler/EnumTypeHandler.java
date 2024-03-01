package com.takaibun.plexmetadatamanager.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.takaibun.plexmetadatamanager.enums.BaseEnum;

/**
 * EnumTypeHandler
 *
 * @author takaibun
 * @since 2024/3/2
 */
@MappedTypes({BaseEnum.class})
@MappedJdbcTypes(JdbcType.TINYINT)
public class EnumTypeHandler extends BaseTypeHandler<BaseEnum> {
    private final HashMap<Integer, BaseEnum> enums = new HashMap<>();

    public EnumTypeHandler(Class<BaseEnum> type) {
        BaseEnum[] enums = type.getEnumConstants();
        if (enums == null) {
            return;
        }
        for (BaseEnum e : enums) {
            this.enums.put(e.getCode(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return enums.get(code);
    }

    @Override
    public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return enums.get(code);
    }

    @Override
    public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return enums.get(code);
    }
}
