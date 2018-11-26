package com.rhea.kernel.service;

import com.github.pagehelper.PageInfo;

/**
 * BaseService接口
 */
public interface PageableService<Entity, Example> extends BaseService<Entity, Example> {

    /**
     * 根据条件分页查询
     *
     * @param example
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Entity> pageByExample(Example example, int pageIndex, int pageSize);
}