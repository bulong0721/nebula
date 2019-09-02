package io.nebula.kernel.exception;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class ServiceException extends NebulaException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
