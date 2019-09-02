package io.nebula.kernel.exception;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
 */
public class NebulaException extends RuntimeException {

    /**
     * @param message
     */
    public NebulaException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public NebulaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public NebulaException(Throwable cause) {
        super(cause);
    }

}
