package io.nebula.poi;

/**
 * Created by 徐步龙 on 2017-09-06.
 */
public interface DataWriter<T> {

    void write(RowWriter writer, T data);
}
