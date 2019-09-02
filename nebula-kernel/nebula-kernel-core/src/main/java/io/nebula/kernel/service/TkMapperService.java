package io.nebula.kernel.service;

import io.nebula.kernel.entity.BaseEntity;

import java.util.List;

/**
 * @author 徐步龙
 */
public interface TkMapperService<Entity extends BaseEntity, Example> {

    /**
     * 根据条件查询记录数量
     *
     * @param example
     * @return
     */
    int countByExample(Example example);

    /**
     * 根据条件判断是否有记录存在
     *
     * @param example
     * @return
     */
    boolean existsByExample(Example example);

    /**
     * 根据条件删除记录
     *
     * @param example
     * @return
     */
    int deleteByExample(Example example);

    /**
     * 根据主键删除记录
     *
     * @param pk
     * @return
     */
    boolean deleteByPK(Object pk);

    /**
     * 插入记录
     *
     * @param entity
     * @return
     */
    boolean insert(Entity entity);

    /**
     * 插入记录有效字段
     *
     * @param entity
     * @return
     */
    boolean insertSelective(Entity entity);

    /**
     * 插入记录并更新主键
     *
     * @param entity
     * @return
     */
    boolean insertUseGeneratedKeys(Entity entity);

    /**
     * 根据条件查询记录(最大只查询100条)
     *
     * @param example
     * @return
     */
    List<Entity> listByExample(Example example);

    /**
     * 根据条件查询第一条记录
     *
     * @param example
     * @return
     */
    Entity findByExample(Example example);

    /**
     * 根据主键查询记录
     *
     * @param pk
     * @return
     */
    Entity findByPK(Object pk);

    /**
     * 根据条件更新有效字段
     *
     * @param entity
     * @param example
     * @return
     */
    int updateByExampleSelective(Entity entity, Example example);

    /**
     * 根据条件更新记录
     *
     * @param entity
     * @param example
     * @return
     */
    int updateByExample(Entity entity, Example example);

    /**
     * 根据主键更新记录有效字段
     *
     * @param entity
     * @return
     */
    boolean updateByPKSelective(Entity entity);

    /**
     * 根据主键更新记录
     *
     * @param entity
     * @return
     */
    boolean updateByPK(Entity entity);

    /**
     * 创建或更新
     *
     * @param entity
     * @return
     */
    boolean saveOrUpdate(Entity entity);

    /**
     * 创建或更新
     *
     * @param entity
     * @return
     */
    boolean saveOrUpdateSelective(Entity entity);

    /**
     * @param entities
     */
    void batchDelete(List<Entity> entities);
}
