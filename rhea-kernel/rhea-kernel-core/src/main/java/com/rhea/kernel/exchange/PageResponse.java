package com.rhea.kernel.exchange;

import lombok.Data;

import java.util.Collection;

/**
 * @author xubulong
 * @version V1.0
 */
@Data
public class PageResponse<T> extends ResponseEntity<Collection<T>> {
    private long total;
    private int page;
    private int size;
}
