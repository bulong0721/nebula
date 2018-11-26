package com.rhea.cache;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/24
 */
public class CachingSelector implements ImportSelector {
    private static String NAMESPACE = "";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[0];
    }
}
