package com.rhea.kernel.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xubulong
 * @version V1.0
 */
public class OptionIntTypeHandler<E extends Enum<E> & Option<Integer>> extends OptionTypeHandler<E, Integer> {
    @Override
    protected void setParameter(PreparedStatement ps, int i, Integer value) throws SQLException {
        ps.setInt(i, value);
    }

    @Override
    protected Integer getValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName);
    }

    @Override
    protected Integer getValue(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getInt(columnIndex);
    }

    @Override
    protected Integer getValue(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getInt(columnIndex);
    }
}
