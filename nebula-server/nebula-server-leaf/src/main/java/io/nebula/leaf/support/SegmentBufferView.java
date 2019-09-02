package io.nebula.leaf.support;

import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/23
 */
@Data
public class SegmentBufferView {
    private String key;
    private long value0;
    private int step0;
    private long max0;

    private long value1;
    private int step1;
    private long max1;
    private int pos;
    private boolean nextReady;
    private boolean initOk;
}
