package io.nebula.kernel.service;

/**
 * @author nebula
 */
public interface IDGenerateService {
    /**
     * DB方式获取ID
     *
     * @return
     */
    Object getSegmentID(String key);

    /**
     * ZK方式获取ID
     *
     * @return
     */
    Object getSnowflakeID(String key);
}
