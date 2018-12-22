package com.rhea.kernel.exchange;

import lombok.Data;
import lombok.ToString;

/**
 * @author xubulong
 * @version V1.0
 */
@Data
@ToString
public class PageRequest<T> extends Pager {
    private T data;
}
