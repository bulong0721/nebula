package com.rhea.kernel.service.impl;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rhea.kernel.entity.BaseEntity;
import com.rhea.kernel.exchange.Pager;
import com.rhea.kernel.mapper.BaseMapper;
import com.rhea.kernel.service.PageableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;
import java.util.List;

/**
 * @author 徐步龙
 */
@Slf4j
public abstract class BaseServiceImpl<Entity extends BaseEntity, Example> implements PageableService<Entity, Example> {

    @Autowired
    protected BaseMapper<Entity> mapper;

    @Autowired
    private Registry springRegistry;

    @Autowired
    private Validator validator;

    protected FluentValidator checkAll() {
        return FluentValidator.checkAll()
                .configure(springRegistry)
                .failOver();
    }

    protected <T> FluentValidator checkAllWithJsr303(T entity) {
        return checkAll()
                .on(entity, new HibernateSupportedValidator<T>().setHiberanteValidator(validator))
                .on(entity);
    }

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
    public boolean deleteByPK(Object pk) {
        return mapper.deleteByPrimaryKey(pk) > 0;
    }

    @Override
    public boolean insert(Entity entity) {
        return mapper.insert(entity) > 0;
    }

    @Override
    public boolean insertSelective(Entity entity) {
        return mapper.insertSelective(entity) > 0;
    }

    @Override
    public boolean insertUseGeneratedKeys(Entity entity) {
        return mapper.insertUseGeneratedKeys(entity) > 0;
    }

    @Override
    public List<Entity> listByExample(Example example) {
        PageInfo<Entity> page = pageByExample(example, 0, 100);
        return page.getList();
    }

    @Override
    public PageInfo<Entity> pageByExample(Example example, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Entity> resultList = mapper.selectByExample(example);
        return new PageInfo<Entity>(resultList);
    }

    @Override
    public PageInfo<Entity> pageByExample(Example example, Pager pager) {
        return pageByExample(example, pager.getPageIndex(), pager.getPageSize());
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
    public boolean updateByPKSelective(Entity entity) {
        return mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public boolean updateByPK(Entity entity) {
        return mapper.updateByPrimaryKey(entity) > 0;
    }

    @Override
    public boolean saveOrUpdate(Entity entity) {
        if (entity.isNew()) {
            return insertUseGeneratedKeys(entity);
        } else {
            return updateByPK(entity);
        }
    }

    @Override
    public boolean saveOrUpdateSelective(Entity entity) {
        if (entity.isNew()) {
            return insertUseGeneratedKeys(entity);
        } else {
            return updateByPKSelective(entity);
        }
    }
}
