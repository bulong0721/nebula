package com.rhea.kernel.exchange;

import lombok.Data;

/**
 * @author xubulong
 * @version V1.0 created at: 2018/10/15
 */
@Data
public class ResponseEntity<T> {
    protected String message = null;
    protected T data;
}
