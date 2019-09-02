/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.nebula.plugin.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * <pre>
 * 名称: SwaggerConfig
 * 描述: swagger配置
 * </pre>
 *
 * @author yangyoupeng
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
@Slf4j
public class SwaggerConfig extends WebMvcConfigurationSupport implements EnvironmentAware {

    private Properties propertyResolver;
    @Autowired(required = false)
    private List<Parameter> defaultParameters;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void setEnvironment(final Environment environment) {
        BindResult<Properties> bindResult = Binder.get(environment).bind("swagger", Properties.class);
        if (bindResult.isBound()) {
            propertyResolver = bindResult.get();
        } else {
            propertyResolver = new Properties();
        }
    }

    /**
     * 装载swagger
     *
     * @return Docket实例
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                // .globalResponseMessage(
                // RequestMethod.GET,
                // Arrays.asList(
                // new ResponseMessage(-50001,"服务器配置错误",null),
                // new ResponseMessage(-40002,"参数缺失",null),
                // new ResponseMessage(-40402,"所请求数据不存在",null),
                // new ResponseMessage(-40100,"认证错误",null),
                // new ResponseMessage(-40000,"一般请求错误",null),
                // new ResponseMessage(-40400,"资源不存在",null),
                // new ResponseMessage(-50000,"一般服务器错误",null),
                // new ResponseMessage(-40001,"参数格式错误",null),
                // new ResponseMessage(-40301,"操作不容许",null),
                // new ResponseMessage(-40302,"没有足够权限",null),
                // new ResponseMessage(-40300,"操作禁止",null),
                // new ResponseMessage(-40401,"所请求方法不存在",null),
                // new ResponseMessage(-50400,"服务器处理超时",null),
                // new ResponseMessage(-50200,"SQL错误",null),
                // new ResponseMessage(-42900,"请求频率超过了限制",null)
                // )
                // )
                .globalOperationParameters(defaultParameters != null && defaultParameters.size() > 0 ? defaultParameters : Collections.emptyList())
                // .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage(propertyResolver.getProperty("basePackage")))
                .paths(PathSelectors.any()).build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        return new ApiInfo(propertyResolver.getProperty("title"), propertyResolver.getProperty("description"),
                propertyResolver.getProperty("version"), propertyResolver.getProperty("termsOfServiceUrl"),
                propertyResolver.getProperty("contact"), propertyResolver.getProperty("license"),
                propertyResolver.getProperty("licenseUrl"));
    }

    /*@Bean
    @Lazy
    public Docket createRestApi(
        @Value("${swagger.title}") String title,
        @Value("${swagger.contact}") String contact,
        @Value("${swagger.version}") String version,
     // @Value("${swagger.description}") String description,
     // @Value("${swagger.url}") String url,
        @Value("${swagger.basePackage}") String basePackage) {

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(new ResponseMessage(-50001, "服务器配置错误", null),
                        new ResponseMessage(-40002, "参数缺失", null), new ResponseMessage(-40402, "所请求数据不存在", null),
                        new ResponseMessage(-40100, "认证错误", null), new ResponseMessage(-40000, "一般请求错误", null),
                        new ResponseMessage(-40400, "资源不存在", null), new ResponseMessage(-50000, "一般服务器错误", null),
                        new ResponseMessage(-40001, "参数格式错误", null), new ResponseMessage(-40301, "操作不容许", null),
                        new ResponseMessage(-40302, "没有足够权限", null), new ResponseMessage(-40300, "操作禁止", null),
                        new ResponseMessage(-40401, "所请求方法不存在", null), new ResponseMessage(-50400, "服务器处理超时", null),
                        new ResponseMessage(-50200, "SQL错误", null), new ResponseMessage(-42900, "请求频率超过了限制", null)))
                .globalOperationParameters(Arrays.asList(new Parameter())).apiInfo(apiInfo(title, contact, version, description, url))
                .select().apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.any()).build();
    }*/

    /*private ApiInfo apiInfo(String title, String contact, String version, String description, String url) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(title).contact(contact).version(version)
                .description(description).licenseUrl(url).termsOfServiceUrl(url)
                .build();

        return apiInfo;
    }*/

}
