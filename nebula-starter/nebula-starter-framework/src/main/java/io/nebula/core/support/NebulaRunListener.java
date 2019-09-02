package io.nebula.core.support;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/2/21
 */
@Slf4j
public class NebulaRunListener implements SpringApplicationRunListener {
    private final SpringApplication application;
    private final String[] args;

    public NebulaRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        loggingStep(context, "SpringBootApplication started success at %s\n %s", null);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        loggingStep(context, "SpringBootApplication started failed at %s\n Exception: %s", exception);
    }

    private void loggingStep(ConfigurableApplicationContext context, String s, Throwable exception) {
        if ("application-1".equalsIgnoreCase(context.getId())) {
            File file = new File("./bootstapt");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String text = String.format(s, sdf.format(new Date()), exception);
            try {
                Files.append(text, file, Charsets.UTF_8);
            } catch (IOException e) {
            }
        }
    }
}
