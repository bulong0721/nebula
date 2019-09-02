package io.nebula.kernel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.nebula.kernel.entity.BaseEntity;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/17
 */
public interface MpBaseMapper<Entity extends BaseEntity> extends BaseMapper<Entity> {
}
