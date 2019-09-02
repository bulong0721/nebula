package io.nebula.plugin;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/19
 */
@Data
public class GenerateConfig extends GlobalConfig {
    private String controllerModule;
    private DataSourceConfig dataSource;
    private PackageConfig packageInfo;
    private StrategyConfig strategy;
    private TemplateConfig template;


    protected ConfigBuilder newConfigBuilder(InjectionConfig injection) {
        ConfigBuilder builder = new ConfigBuilder(packageInfo, dataSource, strategy, template, this);
        builder.setInjectionConfig(injection);
        return builder;
    }
}
