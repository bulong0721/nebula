package com.rhea.cache.compress;

import java.io.ByteArrayInputStream;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/23
 */
public interface ICompressor {

    /**
     * 压缩
     *
     * @param bais
     * @return
     * @throws Exception
     */
    byte[] compress(ByteArrayInputStream bais) throws Exception;

    /**
     * 解压
     *
     * @param bais
     * @return
     * @throws Exception
     */
    byte[] decompress(ByteArrayInputStream bais) throws Exception;
}
