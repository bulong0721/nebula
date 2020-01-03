package io.nebula.poi;


/**
 * Created by 徐步龙 on 2017-10-26.
 */
public interface DataReader<T> {

    T read(RowReader reader);
}
