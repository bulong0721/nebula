package io.nebula.kernel.exchange;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
 */
@Data
@NoArgsConstructor
public class ResponseEntity<T> {
    protected int status = StatusCode.OK.getCode();
    protected String message = null;
    protected T data;

    public boolean success() {
        return status == StatusCode.OK.getCode();
    }
}
