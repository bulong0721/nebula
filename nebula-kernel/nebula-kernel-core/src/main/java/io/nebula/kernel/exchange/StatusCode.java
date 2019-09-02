package io.nebula.kernel.exchange;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/11/28
 */
public enum StatusCode implements IStatusCode {
    /**
     * 业务异常
     */
    OK(2000, "成功"),
    /**
     * 请求错误
     */
    BadRequest(4000, "请求错误"),
    /**
     * 未验证
     */
    Unauthenticated(4001, "未验证"),
    /**
     * 未授权
     */
    Unauthorized(4003, "未授权"),
    /**
     * 未找到资源
     */
    NotFound(4004, "未找到资源"),
    /**
     * 缺少请求参数
     */
    MissingRequestParameter(4101, "缺少请求参数"),
    /**
     * 请求参数校验失败
     */
    ConstraintValidation(4102, "请求参数校验失败"),
    /**
     * 用户名密码不匹配
     */
    BadCredentials(4200, "用户名密码不匹配"),
    /**
     * 请求参数绑定错误
     */
    RequestBinding(4103, "请求参数绑定错误"),
    /**
     * 操作错误
     */
    OperationError(4200, "操作错误"),
    /**
     * 服务器异常
     */
    ServerError(5000, "服务器异常"),
    /**
     * 功能未实现
     */
    NotImplement(5001, "功能未实现"),
    /**
     * 无法提供服务
     */
    ServiceUnavailable(5003, "无法提供服务"),
    /**
     * 业务异常
     */
    ServiceException(5100, "业务异常");

    private final int code;
    private final String reason;

    /**
     * 构造函数
     *
     * @param code
     * @param reason
     */
    StatusCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public static IStatusCode custom(int code, String reason) {
        return new CustomCode(code, reason);
    }

    /**
     * 状态码
     *
     * @return
     */
    @Override
    public int getCode() {
        return code;
    }

    /**
     * 错误原因
     *
     * @return
     */
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
