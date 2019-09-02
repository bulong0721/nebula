package io.nebula.web.controller;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import io.nebula.kernel.security.IUser;
import io.nebula.kernel.service.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
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

    public <T extends IUser> T currentUser() {
        return ServiceContext.currentUser();
    }

}
