package com.rhea.kernel.exchange;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xubulong
 * @version V1.0
 */
@Data
@NoArgsConstructor
public class ResponseEntity<T> {
    protected int status = StatusCode.OK.getCode();
    protected String message = null;
    protected T data;
}
