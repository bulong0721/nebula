package com.rhea.cache.extension;

import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.context.annotation.AdviceMode;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/24
 */
public class ExtensionConfigurationSelector extends CachingConfigurationSelector {

    @Override
    public String[] selectImports(AdviceMode adviceMode) {
        String[] selectImports = super.selectImports(AdviceMode.PROXY);
        selectImports[1] = ExtensionCachingConfiguration.class.getName();
        return selectImports;
    }


}
