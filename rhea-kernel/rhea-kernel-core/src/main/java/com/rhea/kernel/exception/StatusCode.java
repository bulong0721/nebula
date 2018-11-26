package com.rhea.kernel.exception;

/**
 * @author xubulong
 * @version V1.0 created at: 2018/10/16
 */
public interface StatusCode {
    /**
     * 错误代码
     *
     * @return 返回错误代码
     */
    int getCode();

    /**
     * 错误信息
     *
     * @return 返回错误信息
     */
    String getMessage();
}
