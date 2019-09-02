package io.nebula.kernel.exchange;

import com.github.pagehelper.PageInfo;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/20
 */
public abstract class TkToolkit {

    /**
     * 包装分页实体
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageableEntity<T> build(PageInfo<T> page) {
        return StatusCode.OK.build(page.getList(), page.getPageNum(), page.getSize(), page.getTotal());
    }
}
