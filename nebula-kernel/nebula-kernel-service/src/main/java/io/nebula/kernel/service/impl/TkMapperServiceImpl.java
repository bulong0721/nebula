package io.nebula.kernel.service.impl;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.nebula.kernel.entity.BaseEntity;
import io.nebula.kernel.exchange.Pager;
import io.nebula.kernel.mapper.TkBaseMapper;
import io.nebula.kernel.service.ServiceContext;
import io.nebula.kernel.service.TkPageableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

/**
 * @author 徐步龙
 */
@Slf4j
public abstract class TkMapperServiceImpl<Entity extends BaseEntity, Example> implements TkPageableService<Entity, Example> {
    @Autowired
    protected TkBaseMapper<Entity> mapper;
    @Autowired
    private Registry springRegistry;
    @Autowired
    private Validator validator;

    /**
     * Fluent验证器
     *
     * @return
     */
    protected FluentValidator checkAll() {
        return FluentValidator.checkAll()
                .configure(springRegistry)
                .failOver();
    }

    /**
     * Fluent验证器
     *
     * @param entity
     * @param <T>
     * @return
     */
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByPK(Object pk) {
        return mapper.deleteByPrimaryKey(pk) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(Entity entity) {
        ServiceContext.instance().preCreate(entity);
        return mapper.insert(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertSelective(Entity entity) {
        ServiceContext.instance().preCreate(entity);
        return mapper.insertSelective(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUseGeneratedKeys(Entity entity) {
        ServiceContext.instance().preCreate(entity);
        return mapper.insertUseGeneratedKeys(entity) > 0;
    }

    @Override
    public List<Entity> listByExample(Example example) {
        PageInfo<Entity> page = pageByExample(example, 0, 500);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByExampleSelective(Entity entity, Example example) {
        ServiceContext.instance().preUpdate(entity);
        return mapper.updateByExampleSelective(entity, example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByExample(Entity entity, Example example) {
        ServiceContext.instance().preUpdate(entity);
        return mapper.updateByExample(entity, example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateByPKSelective(Entity entity) {
        ServiceContext.instance().preUpdate(entity);
        return mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateByPK(Entity entity) {
        ServiceContext.instance().preUpdate(entity);
        return mapper.updateByPrimaryKey(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(Entity entity) {
        if (entity.isNew()) {
            return insertUseGeneratedKeys(entity);
        } else {
            return updateByPK(entity);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateSelective(Entity entity) {
        if (entity.isNew()) {
            return insertUseGeneratedKeys(entity);
        } else {
            return updateByPKSelective(entity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Entity> entities) {
        if (null == entities) {
            return;
        }
        for (Entity entity : entities) {
            this.deleteByPK(entity);
        }
    }
}
