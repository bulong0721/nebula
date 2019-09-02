package io.nebula.properties;

import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@Data
public class Scheduler {
    private int interval;
    private int timeout;
}
