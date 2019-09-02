package io.nebula.kernel.exception;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/15
 */
public class MapperException extends NebulaException {

    public MapperException(String message) {
        super(message);
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapperException(Throwable cause) {
        super(cause);
    }
}
