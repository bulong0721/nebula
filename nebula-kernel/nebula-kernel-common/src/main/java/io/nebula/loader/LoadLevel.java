package io.nebula.loader;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/7
 */
public @interface LoadLevel {
    /**
     * Name string.
     *
     * @return the string
     */
    String name();

    /**
     * Order int.
     *
     * @return the int
     */
    int order();
}
