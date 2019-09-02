package io.nebula.kernel.service;

import com.github.pagehelper.PageInfo;
import io.nebula.kernel.entity.BaseEntity;
import io.nebula.kernel.exchange.Pager;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public interface TkPageableService<Entity extends BaseEntity, Example> extends TkMapperService<Entity, Example> {

    /**
     * 根据条件分页查询
     *
     * @param example
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Entity> pageByExample(Example example, int pageIndex, int pageSize);

    /**
     * 根据条件分页查询
     *
     * @param example
     * @param pager
     * @return
     */
    PageInfo<Entity> pageByExample(Example example, Pager pager);
}
