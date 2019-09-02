package io.nebula.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/27
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RandomStringTest {
    private RandomString randomString;

    @Before
    public void setUp() {
        randomString = new RandomString();
    }

    @org.junit.Test
    public void random() {
        String random = randomString.random(6, "1234567890");
        System.out.println(random);
        Assert.assertNotNull(random);
    }
}
