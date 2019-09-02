package io.nebula.schedule.annotation;

import io.nebula.schedule.configuration.JobConfiguration;
import io.nebula.schedule.configuration.JobScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({JobConfiguration.class, JobScannerRegistrar.class})
public @interface EnableSchedule {
    String[] value() default {"io.nebula"};
}
