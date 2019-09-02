package io.nebula.kernel.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/13
 */
public class OptionStringTypeHandler<E extends Enum<E> & Option<String>> extends OptionTypeHandler<E, String> {
    @Override
    protected void setParameter(PreparedStatement ps, int i, String value) throws SQLException {
        ps.setString(i, value);
    }

    @Override
    protected E convert2Option(EnumSet<E> enumSet, String value) {
        for (E elem : enumSet) {
            if (elem.getValue().equalsIgnoreCase(value)) {
                return elem;
            }
        }
        return null;
    }

    @Override
    protected String getValue(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    protected String getValue(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    protected String getValue(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
