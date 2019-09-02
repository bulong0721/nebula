package io.nebula.kernel.typehandler;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/13
 */
public interface Option<T> {

    /**
     * 选项值
     *
     * @return
     */
    T getValue();

    /**
     * 选项显示
     *
     * @return
     */
    String getLabel();
}
