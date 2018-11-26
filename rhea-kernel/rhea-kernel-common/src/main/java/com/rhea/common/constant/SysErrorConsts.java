/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rhea.common.constant;

/**
 * <pre>
 * 名称: SysErrorConsts
 * 描述: 错误常量
 * </pre>
 *
 * @author yangyoupeng
 * @since 1.0.0
 */
@SuppressWarnings("all")
public interface SysErrorConsts {

    /**
     * 系统错误: 50000
     */
    int SYS_ERROR_CODE = 50000;

    /**
     * 数据库错误: 5000
     */
    int DB_ERROR_CODE = 5000;

    /**
     * 服务器配置错误
     */
    int CONFIGURATION_ERROR_CODE = -50001;

    /**
     * 认证错误
     */
    int AUTHENTICATION_ERROR_CODE = -40100;

    /**
     * 请求频率超过了限制
     */
    int CALLRATE_ERROR_CODE = -42900;

    /**
     * 操作禁止
     */
    int OPERATION_ERROR_CODE = -40300;

    /**
     * 一般请求错误
     */
    int REQUEST_ERROR_CODE = -40000;

    /**
     * 资源不存在
     */
    int RESOURCE_ERROR_CODE = -40400;

    /**
     * 一般服务器错误
     */
    int SERVER_ERROR_CODE = -50000;

    /**
     * 参数格式错误
     */
    int INVALID_ARGUMENT_ERROR_CODE = -40001;

    /**
     * 操作不容许
     */
    int INVALID_OPERATION_ERROR_CODE = -40301;

    /**
     * 没有足够权限
     */
    int PERMISSION_ERROR_CODE = -40302;

    /**
     * 参数缺失
     */
    int MISSING_ARGUMETN_ERROR_CODE = -40002;

    /**
     * 所请求数据不存在
     */
    int MISSING_DATA_ERROR_CODE = -40402;

    /**
     * 所请求方法不存在
     */
    int MISSING_METHOD_ERROR_CODE = -40401;

    /**
     * 服务器处理超时
     */
    int SERVER_TIMEOUT_ERROR_CODE = -50400;

    /**
     * NOT_FOUND错误
     */
    int NOT_FOUND_ERROR_CODE = -40404;

    /**
     * SECRET错误
     */
    int SECRET_ERROR_CODE = -50100;

    /**
     * SQL错误
     */
    int SQL_ERROR_CODE = -50200;

}
