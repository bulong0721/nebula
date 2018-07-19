package com.rhea.common.base;

import lombok.Data;

/**
 * 统一返回结果类
 */
@Data
public class BaseResult<T> {
    /**
     * 状态码：1成功，其他为失败
     */
    private int code;
    /**
     * 成功为success，其他为失败原因
     */
    private String message;
    /**
     * 数据结果集
     */
    private T data;
}
