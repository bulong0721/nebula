package io.nebula.schedule.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class})
@EnableSchedule("io.nebula.schedule.annotation")
@PropertySource("classpath:application-dev.properties")
public class ApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(6000);
        Assert.assertTrue("测试SimpleJob执行次数不符合预期", ScheduleTest.counter.get() >= 5);
    }
}
