package io.nebula.io;

import io.nebula.buffer.FastCharBuffer;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/25
 */
public class FastCharArrayWriter extends Writer {

    private final FastCharBuffer buffer;

    /**
     * Creates a new writer. The buffer capacity is
     * initially 1024 bytes, though its size increases if necessary.
     */
    public FastCharArrayWriter() {
        this(1024);
    }

    /**
     * Creates a new char array {@link Writer}, with a buffer capacity of
     * the specified size, in bytes.
     *
     * @param size the initial size.
     * @throws IllegalArgumentException if size is negative.
     */
    public FastCharArrayWriter(final int size) {
        buffer = new FastCharBuffer(size);
    }

    /**
     * @see Writer#write(char[], int, int)
     */
    @Override
    public void write(final char[] b, final int off, final int len) {
        buffer.append(b, off, len);
    }

    /**
     * Writes single byte.
     */
    @Override
    public void write(final int b) {
        buffer.append((char) b);
    }

    @Override
    public void write(final String s, final int off, final int len) {
        write(s.toCharArray(), off, len);
    }

    /**
     * @see CharArrayWriter#size()
     */
    public int size() {
        return buffer.size();
    }

    /**
     * Closing a {@link FastCharArrayWriter} has no effect. The methods in
     * this class can be called after the stream has been closed without
     * generating an {@link IOException}.
     */
    @Override
    public void close() {
        //nop
    }

    /**
     * Flushing a {@link FastCharArrayWriter} has no effects.
     */
    @Override
    public void flush() {
        //nop
    }

    /**
     * @see CharArrayWriter#reset()
     */
    public void reset() {
        buffer.clear();
    }

    /**
     * @see CharArrayWriter#writeTo(Writer)
     */
    public void writeTo(final Writer out) throws IOException {
        out.write(buffer.toArray());
    }

    /**
     * @see CharArrayWriter#toCharArray()
     */
    public char[] toCharArray() {
        return buffer.toArray();
    }

    /**
     * @see CharArrayWriter#toString()
     */
    @Override
    public String toString() {
        return new String(toCharArray());
    }
}
