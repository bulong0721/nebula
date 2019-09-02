package io.nebula.kernel.exception;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/19
 */
public class LoginException extends Exception {

    /**
     * 错误原因
     */
    private final Reason reason;

    public LoginException(Reason reason) {
        super("");
        this.reason = reason;
    }

    /**
     * 构造函数
     *
     * @param reason
     * @param cause
     */
    public LoginException(Reason reason, Throwable cause) {
        super("", cause);
        this.reason = reason;
    }

    /**
     * 错误原因
     *
     * @return
     */
    public Reason getReason() {
        return reason;
    }

    public enum Reason {
        /**
         * 没找到账号
         */
        NotFound,
        /**
         * 账号被锁定
         */
        Locked,
        /**
         * 账号已过期
         */
        Expired,
        /**
         * 用户名密码不匹配
         */
        BadCredential;

        public LoginException buildException() {
            return new LoginException(this);
        }
    }
}
