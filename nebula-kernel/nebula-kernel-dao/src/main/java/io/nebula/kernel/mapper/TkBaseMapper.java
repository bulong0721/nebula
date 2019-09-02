package io.nebula.kernel.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
 */
public interface TkBaseMapper<T> extends Mapper<T>, InsertUseGeneratedKeysMapper<T>, IdsMapper<T> {
}
