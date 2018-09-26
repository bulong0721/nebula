package com.rhea.rpc.annotation;

import com.rhea.rpc.configuration.RemoteConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({RemoteConfiguration.class})
public @interface EnableRemote {
    boolean tolerate() default false;
}
