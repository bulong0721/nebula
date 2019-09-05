package io.nebula.kernel.exchange;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/20
 */
public abstract class MpToolkit {

    /**
     * 包装分页实体
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageableEntity<T> build(IPage<T> page) {
        return StatusCode.OK.build(page.getRecords(), (int) page.getCurrent(), (int) page.getSize(), page.getTotal());
    }

    /**
     * 转换为IPage
     *
     * @param pager
     * @param <T>l
     * @return
     */
    public static <T> Page<T> toPage(Pager pager) {
        return new Page<>(pager.getPageIndex(), pager.getPageSize());
    }

    /**
     * 查询条件
     *
     * @param request
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> queryWrapper(PageRequest<T> request) {
        return new QueryWrapper<>(request.getData());
    }

    /**
     * 查询条件
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> queryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 更新条件
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> UpdateWrapper<T> updateWrapper(T entity) {
        return new UpdateWrapper<>(entity);
    }

}
