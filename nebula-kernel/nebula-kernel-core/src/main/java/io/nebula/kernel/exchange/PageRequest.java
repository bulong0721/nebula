package io.nebula.kernel.exchange;

import lombok.Data;
import lombok.ToString;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/13
 */
@Data
@ToString
public class PageRequest<T> extends Pager {
    private T data;
}
