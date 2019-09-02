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
}
