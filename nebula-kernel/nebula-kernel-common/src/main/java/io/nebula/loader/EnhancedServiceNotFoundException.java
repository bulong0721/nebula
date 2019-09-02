package io.nebula.loader;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/7
 */
public class EnhancedServiceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 7748438218914409019L;

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     */
    public EnhancedServiceNotFoundException(String errorCode) {
        super(errorCode);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     * @param cause     the cause
     */
    public EnhancedServiceNotFoundException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     * @param errorDesc the error desc
     */
    public EnhancedServiceNotFoundException(String errorCode, String errorDesc) {
        super(errorCode + ":" + errorDesc);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     * @param errorDesc the error desc
     * @param cause     the cause
     */
    public EnhancedServiceNotFoundException(String errorCode, String errorDesc, Throwable cause) {
        super(errorCode + ":" + errorDesc, cause);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param cause the cause
     */
    public EnhancedServiceNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
