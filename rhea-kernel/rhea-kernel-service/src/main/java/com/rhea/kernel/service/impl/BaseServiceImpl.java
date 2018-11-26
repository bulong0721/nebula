package com.rhea.kernel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhea.kernel.mapper.BaseMapper;
import com.rhea.kernel.service.PageableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xubulong8
 */
@Slf4j
public abstract class BaseServiceImpl<Entity, Example> implements PageableService<Entity, Example> {

    @Autowired
    protected BaseMapper<Entity> mapper;

    @Override
    public int countByExample(Example example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public boolean existsByExample(Example example) {
        return mapper.selectCountByExample(example) > 0;
    }

    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPK(Object pk) {
        return mapper.deleteByPrimaryKey(pk);
    }

    @Override
    public int insert(Entity entity) {
        return mapper.insert(entity);
    }

    @Override
    public int insertSelective(Entity entity) {
        return mapper.insertSelective(entity);
    }

    @Override
    public List<Entity> listByExample(Example example) {
        PageInfo<Entity> page = pageByExample(example, 0, 100);
        return page.getList();
    }

    @Override
    public PageInfo<Entity> pageByExample(Example example, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex * pageSize, pageSize);
        List<Entity> resultList = mapper.selectByExample(example);
        return new PageInfo<Entity>(resultList);
    }

    @Override
    public Entity findByExample(Example example) {
        return mapper.selectOneByExample(example);
    }

    @Override
    public Entity findByPK(Object pk) {
        return mapper.selectByPrimaryKey(pk);
    }

    @Override
    public int updateByExampleSelective(Entity entity, Example example) {
        return mapper.updateByExampleSelective(entity, example);
    }

    @Override
    public int updateByExample(Entity entity, Example example) {
        return mapper.updateByExample(entity, example);
    }

    @Override
    public int updateByPKSelective(Entity entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByPK(Entity entity) {
        return mapper.updateByPrimaryKey(entity);
    }
}