package com.rhea.kernel.exception;

/**
 * @author xubulong
 * @version V1.0
 */
public class LoginException extends Exception {

    public enum Reason {
        NotFound,
        Locked,
        Expired,
        BadCredential
    }

    private final Reason reason;

    public LoginException(Reason reason) {
        super("");
        this.reason = reason;
    }

    public LoginException(Reason reason, Throwable cause) {
        super("", cause);
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
}
