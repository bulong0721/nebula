package io.nebula.http;

import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/28
 */
@Data
public class HttpClientResult {
    private final int code;
    private final String content;

    public HttpClientResult(int code) {
        this(code, null);
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public boolean isSuccess() {
        return 200 == code;
    }
}
