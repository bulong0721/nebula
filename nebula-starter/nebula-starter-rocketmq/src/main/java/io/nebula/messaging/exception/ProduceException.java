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

    public ProduceException(Throwable cause) {
        super(cause);
    }
}
