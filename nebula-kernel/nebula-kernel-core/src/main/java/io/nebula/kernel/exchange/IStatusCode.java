package io.nebula.kernel.exchange;

import java.util.Collection;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/5
 */
public interface IStatusCode {
    /**
     * 状态码
     *
     * @return
     */
    int getCode();

    /**
     * 错误原因
     *
     * @return
     */
    String getReason();

    /**
     * 判断是否为信息返回
     *
     * @return
     */
    default boolean is1xxxInformational() {
        return getCode() >= 1000 && getCode() < 2000;
    }

    /**
     * 判断是否为成功返回
     *
     * @return
     */
    default boolean is2xxxSuccessful() {
        return getCode() >= 2000 && getCode() < 3000;
    }

    /**
     * 判断是否为重定向
     *
     * @return
     */
    default boolean is3xxxRedirection() {
        return getCode() >= 3000 && getCode() < 4000;
    }

    /**
     * 判断是否是客户端错误
     *
     * @return
     */
    default boolean is4xxxClientError() {
        return getCode() >= 4000 && getCode() < 5000;
    }

    /**
     * 判断是否是服务端错误
     *
     * @return
     */
    default boolean is5xxxServerError() {
        return getCode() >= 5000;
    }

    /**
     * 构建分页响应实体
     *
     * @param list
     * @param pager
     * @param total
     * @param <T>
     * @return
     */
    default <T> PageableEntity<T> build(Collection<T> list, Pager pager, long total) {
        assert is2xxxSuccessful();
        PageableEntity<T> response = new PageableEntity<T>(list, pager, total);
        response.setStatus(getCode());
        response.setMessage(getReason());
        return response;
    }

    /**
     * 构建分页响应实体
     *
     * @param list
     * @param page
     * @param size
     * @param total
     * @param <T>
     * @return
     */
    default <T> PageableEntity<T> build(Collection<T> list, int page, int size, long total) {
        assert is2xxxSuccessful();
        PageableEntity<T> response = new PageableEntity<T>(list, page, size, total);
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
    public default ResponseEntity build(Object data) {
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
    default ResponseEntity build() {
        return build(null);
    }
}
