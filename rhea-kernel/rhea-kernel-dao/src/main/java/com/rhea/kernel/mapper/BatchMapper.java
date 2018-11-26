package com.rhea.kernel.mapper;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.mapping.SqlCommandType;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

import java.util.function.Function;

/**
 * @author xubulong
 * @version V1.0 created at: 2018/11/5
 */
public interface BatchMapper<T> extends BaseInsertMapper<T>, BaseUpdateMapper<T>, BaseDeleteMapper<T> {

    int commitPerBatch = 100;

    /**
     * 提交数据库
     */
    @Flush
    void flush();

    /**
     * 批量执行操作
     *
     * @param iterable
     * @param cmdType
     * @param function
     */
    default void batchExecute(Iterable<T> iterable, SqlCommandType cmdType, Function<T, Integer> function) {
        int index = 0;
        for (T record : iterable) {
            function.apply(record);
            if (needCommit(index++, cmdType)) {
                this.flush();
            }
        }
    }

    /**
     * 批量插入
     *
     * @param iterable
     * @return
     */
    default int batchInsert(Iterable<T> iterable) {
        batchExecute(iterable, SqlCommandType.INSERT, this::insert);
        return 0;
    }

    /**
     * 批量插入
     *
     * @param iterable
     * @return
     */
    default int batchInsertSelective(Iterable<T> iterable) {
        batchExecute(iterable, SqlCommandType.INSERT, this::insertSelective);
        return 0;
    }

    /**
     * 批量更新
     *
     * @param iterable
     * @return
     */
    default int batchUpdateByPK(Iterable<T> iterable) {
        batchExecute(iterable, SqlCommandType.UPDATE, this::updateByPrimaryKey);
        return 0;
    }

    /**
     * 批量更新
     *
     * @param iterable
     * @return
     */
    default int batchUpdateByPKSelective(Iterable<T> iterable) {
        batchExecute(iterable, SqlCommandType.UPDATE, this::updateByPrimaryKeySelective);
        return 0;
    }

    /**
     * 批量删除
     *
     * @param iterable
     * @return
     */
    default int batchDeleteByPK(Iterable<T> iterable) {
        batchExecute(iterable, SqlCommandType.DELETE, this::deleteByPrimaryKey);
        return 0;
    }

    /**
     * 批量删除
     *
     * @param iterable
     * @return
     */
    default int batchDelete(Iterable<T> iterable) {
        batchExecute(iterable, SqlCommandType.DELETE, this::delete);
        return 0;
    }

    /**
     * 判断是否需要阶段性提交
     *
     * @param index
     * @param cmdType
     * @return
     */
    default boolean needCommit(int index, SqlCommandType cmdType) {
        return index % commitPerBatch == 0;
    }
}
