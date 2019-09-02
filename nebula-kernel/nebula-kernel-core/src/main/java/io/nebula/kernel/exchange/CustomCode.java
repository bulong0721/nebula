package io.nebula.kernel.exchange;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/5
 */
public class CustomCode implements IStatusCode {
    private final int code;
    private final String reason;

    public CustomCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code=" + code +
                ", reason='" + reason + '\'' +
                '}';
    }
}
