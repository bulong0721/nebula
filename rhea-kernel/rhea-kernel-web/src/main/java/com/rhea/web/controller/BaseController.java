package com.rhea.web.controller;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * @author xubulong
 * @version V1.0
 */
public abstract class BaseController {
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
}
