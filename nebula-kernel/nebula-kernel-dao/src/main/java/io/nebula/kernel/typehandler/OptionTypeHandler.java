package io.nebula.kernel.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.ParameterizedType;
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
public abstract class OptionTypeHandler<E extends Enum<E> & Option<T>, T> extends BaseTypeHandler<E> {
    protected final Class<E> clazz;
    protected final EnumSet<E> enumSet;

    public OptionTypeHandler() {
        clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        enumSet = EnumSet.allOf(clazz);
    }

    /**
     * 设置参数
     *
     * @param ps
     * @param i
     * @param value
     * @throws SQLException
     */
    protected abstract void setParameter(PreparedStatement ps, int i, T value) throws SQLException;

    /**
     * 数据库值转换为枚举
     *
     * @param enumSet
     * @param value
     * @return
     */
    protected E convert2Option(EnumSet<E> enumSet, T value) {
        for (E elem : enumSet) {
            if (value == elem.getValue()) {
                return elem;
            }
        }
        return null;
    }

    /**
     * 取值
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    protected abstract T getValue(ResultSet rs, String columnName) throws SQLException;

    /**
     * 取值
     *
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    protected abstract T getValue(ResultSet rs, int columnIndex) throws SQLException;

    /**
     * 取值
     *
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    protected abstract T getValue(CallableStatement cs, int columnIndex) throws SQLException;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        setParameter(ps, i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        T value = getValue(rs, columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return convert2Option(enumSet, value);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + value + " to " + clazz.getSimpleName() + " by ordinal value.", ex);
            }
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        T value = getValue(rs, columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return convert2Option(enumSet, value);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + value + " to " + clazz.getSimpleName() + " by ordinal value.", ex);
            }
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        T value = getValue(cs, columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            try {
                return convert2Option(enumSet, value);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + value + " to " + clazz.getSimpleName() + " by ordinal value.", ex);
            }
        }
    }
}
