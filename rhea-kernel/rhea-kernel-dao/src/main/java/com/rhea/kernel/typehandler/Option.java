package com.rhea.kernel.typehandler;

/**
 * @author xubulong
 * @version V1.0
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
