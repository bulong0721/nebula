package com.rhea.common.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * BaseService接口
 */
public interface BaseService<Entity, Example> {

    /**
     * 根据条件查询记录数量
     * @param example
     * @return
     */
    int countByExample(Example example);

    /**
     * 根据条件删除记录
     * @param example
     * @return
     */
    int deleteByExample(Example example);

    /**
     * 根据主键删除记录
     * @param pk
     * @return
     */
    int deleteByPK(Object pk);

    /**
     * 插入记录
     * @param entity
     * @return
     */
    int insert(Entity entity);

    /**
     * 插入记录有效字段
     * @param entity
     * @return
     */
    int insertSelective(Entity entity);

    /**
     * 根据条件查询记录(最大只查询100条)
     * @param example
     * @return
     */
    List<Entity> listByExample(Example example);

    /**
     * 根据条件分页查询
     * @param example
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageInfo<Entity> pageByExample(Example example, int pageIndex, int pageSize);

    /**
     * 根据条件查询第一条记录
     * @param example
     * @return
     */
    Entity findByExample(Example example);

    /**
     * 根据主键查询记录
     * @param pk
     * @return
     */
    Entity findByPK(Object pk);

    /**
     * 根据条件更新有效字段
     * @param entity
     * @param example
     * @return
     */
    int updateByExampleSelective(Entity entity, Example example);

    /**
     * 根据条件更新记录
     * @param entity
     * @param example
     * @return
     */
    int updateByExample(Entity entity, Example example);

    /**
     * 根据主键更新记录有效字段
     * @param entity
     * @return
     */
    int updateByPKSelective(Entity entity);

    /**
     * 根据主键更新记录
     * @param entity
     * @return
     */
    int updateByPK(Entity entity);

}