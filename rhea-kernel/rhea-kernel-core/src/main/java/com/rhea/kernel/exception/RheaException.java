package com.rhea.kernel.exception;

/**
 * @author xubulong
 * @version V1.0
 */
public class RheaException extends RuntimeException {

    /**
     * @param message
     */
    public RheaException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public RheaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public RheaException(Throwable cause) {
        super(cause);
    }

}
