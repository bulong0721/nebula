package io.nebula.kernel.service.impl;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.nebula.kernel.entity.BaseEntity;
import io.nebula.kernel.mapper.MpBaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/17
 */
@Slf4j
public abstract class MybatisPlusServiceImpl<M extends MpBaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> {
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
}
