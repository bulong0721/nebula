package io.nebula.messaging.exception;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
public class ConsumeException extends RuntimeException {

    public ConsumeException() {
    }

    public ConsumeException(String message) {
        super(message);
    }

    public ConsumeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumeException(Throwable cause) {
        super(cause);
    }

    public ConsumeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
