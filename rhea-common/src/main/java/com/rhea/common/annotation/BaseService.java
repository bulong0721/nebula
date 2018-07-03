package com.rhea.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
