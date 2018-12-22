package com.rhea.kernel.exchange;

import com.github.pagehelper.PageInfo;

/**
 * @author xubulong
 * @version V1.0
 */
public enum StatusCode {
    /**
     * 业务异常
     */
    OK(2000, "成功"),
    /**
     * 请求错误
     */
    BadRequest(4000, "请求错误"),
    /**
     * 未授权
     */
    Unauthorized(4001, "未授权"),
    /**
     * 禁止访问
     */
    Forbidden(4003, "禁止访问"),
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

    /**
     * 状态码
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 错误原因
     *
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     * 判断是否为信息返回
     *
     * @return
     */
    public boolean is1xxxInformational() {
        return code >= 1000 && code < 2000;
    }

    /**
     * 判断是否为成功返回
     *
     * @return
     */
    public boolean is2xxxSuccessful() {
        return code >= 2000 && code < 3000;
    }

    /**
     * 判断是否为重定向
     *
     * @return
     */
    public boolean is3xxxRedirection() {
        return code >= 3000 && code < 4000;
    }

    /**
     * 判断是否是客户端错误
     *
     * @return
     */
    public boolean is4xxxClientError() {
        return code >= 4000 && code < 5000;
    }

    /**
     * 判断是否是服务端错误
     *
     * @return
     */
    public boolean is5xxxServerError() {
        return code >= 5000;
    }

    /**
     * 构建分页响应实体
     *
     * @param page
     * @param <T>
     * @return
     */
    public <T> PageableEntity<T> build(PageInfo<T> page) {
        assert is2xxxSuccessful();
        PageableEntity<T> response = new PageableEntity<T>(page);
        response.setStatus(getCode());
        response.setMessage(getReason());
        return response;
    }

    /**
     * 构建响应实体
     *
     * @param data
     * @return
     */
    public ResponseEntity build(Object data) {
        ResponseEntity response = new ResponseEntity();
        response.setData(data);
        response.setStatus(getCode());
        response.setMessage(getReason());
        return response;
    }

    /**
     * 构建响应实体
     *
     * @return
     */
    public ResponseEntity build() {
        return build(null);
    }
}
