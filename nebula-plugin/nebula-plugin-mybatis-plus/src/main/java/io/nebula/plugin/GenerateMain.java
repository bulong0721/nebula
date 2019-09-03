package io.nebula.plugin;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/19
 */
public class GenerateMain {

    public static void main(String[] args) throws Exception {
        if (null == args || args.length < 1) {
            throw new Exception("配置文件不能为空");
        }
        GenerateConfig config = getConfig(args[0]);
        defaultTemplate(config);

        AutoGenerator generator = new AutoGenerator();

        generator.setGlobalConfig(config);
        InjectionConfig injection = new InjectionConfig();
        generator.setCfg(injection);
        generator.setDataSource(config.getDataSource());
        generator.setStrategy(config.getStrategy());
        generator.setTemplate(config.getTemplate());
        generator.setPackageInfo(config.getPackageInfo());
        generator.setConfig(config.newConfigBuilder(injection));

        // set freemarker engine
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 切换Controller生成目录到xxx-ms中
        changeControllerDir(generator, config);
        generator.execute();
    }

    private static ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    private static GenerateConfig getConfig(String path) throws IOException {
        File yamlFile = new File(path);
        return objectMapper.readValue(yamlFile, GenerateConfig.class);
    }

    private static void defaultTemplate(GenerateConfig config) {
        TemplateConfig template = config.getTemplate();
        template.setEntity("/template/entity.java");
        template.setMapper("/template/mapper.java");
        template.setXml("/template/mapper.xml");
        template.setService("/template/service.java");
        template.setServiceImpl("/template/serviceImpl.java");
        template.setController("/template/controller.java");
    }

    private static void changeControllerDir(AutoGenerator generator, GenerateConfig config) {
        ConfigBuilder builder = generator.getConfig();
        Map<String, String> pathInfo = builder.getPathInfo();
        String path = pathInfo.get(ConstVal.CONTROLLER_PATH);
        if (isNotEmpty(path) && isNotEmpty(config.getControllerModule())) {
            String newPath = String.format("../%s/%s", config.getControllerModule(), path);
            pathInfo.put(ConstVal.CONTROLLER_PATH, newPath);
        }
        path = pathInfo.get(ConstVal.XML_PATH);
        if (isNotEmpty(path)) {
            pathInfo.put(ConstVal.XML_PATH, "src/main/resources/mapper");
        }
    }

    private static boolean isNotEmpty(String text) {
        return text != null && !text.isEmpty();
    }

}
