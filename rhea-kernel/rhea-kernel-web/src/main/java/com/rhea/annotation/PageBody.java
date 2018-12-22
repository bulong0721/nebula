package com.rhea.annotation;

import java.lang.annotation.*;

/**
 * @author xubulong
 * @version V1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageBody {
}
