package com.rhea.kernel.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

/**
 * @author xubulong
 * @version V1.0
 */
public interface BaseMapper<T> extends Mapper<T>, InsertUseGeneratedKeysMapper<T>, IdsMapper<T> {
}
