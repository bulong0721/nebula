package io.nebula.messaging.support;

import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/18
 */
@Data
public class EDSWrapper<T> {
    private Header header;
    private T payload;

    @Data
    public static class Header {

    }
}
