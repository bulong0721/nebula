package io.nebula.kernel.exception;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
 */
public class NebulaException extends RuntimeException {

    public NebulaException() {
    }

    public NebulaException(String message) {
        super(message);
    }

    public NebulaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NebulaException(Throwable cause) {
        super(cause);
    }

    public NebulaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
