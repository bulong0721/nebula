package com.rhea.schedule.annotation;

import com.rhea.schedule.configuration.JobConfiguration;
import com.rhea.schedule.configuration.JobScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({JobConfiguration.class, JobScannerRegistrar.class})
public @interface EnableSchedule {
    String[] value() default {"com.rhea"};
}
