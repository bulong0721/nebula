package io.nebula.seata.feign;

import feign.Feign;
import org.springframework.beans.factory.BeanFactory;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
final class SeataFeignBuilder {
    private SeataFeignBuilder() {
    }

    static Feign.Builder builder(BeanFactory beanFactory) {
        return Feign.builder().client(new SeataFeignClient(beanFactory));
    }
}
