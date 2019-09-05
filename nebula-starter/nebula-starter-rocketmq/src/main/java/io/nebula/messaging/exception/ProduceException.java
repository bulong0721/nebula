package io.nebula.messaging.exception;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
public class ProduceException extends RuntimeException {

    public ProduceException() {
    }

    public ProduceException(String message) {
        super(message);
    }

    public ProduceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProduceException(Throwable cause) {
        super(cause);
    }

    public ProduceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
