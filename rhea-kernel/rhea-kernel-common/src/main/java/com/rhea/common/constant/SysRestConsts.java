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
 * 名称: SysRestConsts
 * 描述: rest接口相关常量
 * </pre>
 *
 * @author yangyoupeng
 * @since 1.0.0
 */
@SuppressWarnings("all")
public interface SysRestConsts {

    /**
     * REQUEST_ID:requestId
     */
    String REQUEST_ID = "requestId";
    /**
     * SERVER_IP:serverIp
     */
    String SERVER_IP = "serverIp";
    /**
     * CLINET_IP:clientIp
     */
    String CLINET_IP = "clientIp";
    /**
     * FEIGN_SOURCE:X-Yto-CallBack
     */
    String FEIGN_SOURCE = "X-Yto-CallBack";
    /**
     * 超时：10000
     */
    int HTTP_TIMEOUT = 10000;
}
