package io.nebula.io;

import java.io.Reader;
import java.nio.CharBuffer;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class CharBufferReader extends Reader {

    private final CharBuffer charBuffer;

    public CharBufferReader(final CharBuffer charBuffer) {
        // duplicate so to allow to move independently,
        // but share the same underlying data.
        this.charBuffer = charBuffer.duplicate();
    }

    @Override
    public int read(final char[] chars, final int offset, final int length) {
        int read = Math.min(charBuffer.remaining(), length);
        charBuffer.get(chars, offset, read);
        return read;
    }

    @Override
    public int read() {
        return charBuffer.position() < charBuffer.limit() ? charBuffer.get() : -1;
    }

    @Override
    public void close() {
    }

}
