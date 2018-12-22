package com.rhea.kernel.service;

import com.github.pagehelper.PageInfo;
import com.rhea.kernel.entity.BaseEntity;
import com.rhea.kernel.exchange.Pager;

/**
 * BaseService接口
 */
public interface PageableService<Entity extends BaseEntity, Example> extends BaseService<Entity, Example> {

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
