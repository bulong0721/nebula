package io.nebula.buffer;

import io.nebula.CharArraySequence;

import java.util.Arrays;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class FastCharBuffer implements CharSequence, Appendable {

    private char[] buffer;
    private int offset;

    /**
     * Creates a new {@code char} buffer. The buffer capacity is
     * initially 64 chars, though its size increases if necessary.
     */
    public FastCharBuffer() {
        this.buffer = new char[64];
    }

    /**
     * Creates a new {@code char} buffer, with a buffer capacity of
     * the specified size, in chars.
     *
     * @param size the initial size.
     * @throws IllegalArgumentException if size is negative.
     */
    public FastCharBuffer(final int size) {
        this.buffer = new char[size];
    }

    /**
     * Grows the buffer.
     */
    private void grow(final int minCapacity) {
        final int oldCapacity = buffer.length;
        int newCapacity = oldCapacity << 1;
        if (newCapacity - minCapacity < 0) {
            // special case, min capacity is larger then a grow
            newCapacity = minCapacity + 512;
        }
        buffer = Arrays.copyOf(buffer, newCapacity);
    }

    /**
     * Appends single {@code char} to buffer.
     */
    @Override
    public FastCharBuffer append(final char element) {
        if (offset - buffer.length >= 0) {
            grow(offset);
        }

        buffer[offset++] = element;
        return this;
    }

    /**
     * Appends {@code char} array to buffer.
     */
    public FastCharBuffer append(final char[] array, final int off, final int len) {
        if (offset + len - buffer.length > 0) {
            grow(offset + len);
        }

        System.arraycopy(array, off, buffer, offset, len);
        offset += len;
        return this;
    }

    /**
     * Appends {@code char} array to buffer.
     */
    public FastCharBuffer append(final char[] array) {
        return append(array, 0, array.length);
    }

    /**
     * Appends another fast buffer to this one.
     */
    public FastCharBuffer append(final FastCharBuffer buff) {
        if (buff.offset == 0) {
            return this;
        }
        append(buff.buffer, 0, buff.offset);
        return this;
    }

    /**
     * Returns buffer size.
     */
    public int size() {
        return offset;
    }

    @Override
    public int length() {
        return offset;
    }

    /**
     * Tests if this buffer has no elements.
     */
    public boolean isEmpty() {
        return offset == 0;
    }

    /**
     * Resets the buffer content.
     */
    public void clear() {
        offset = 0;
    }

    /**
     * Creates {@code char} array from buffered content.
     */
    public char[] toArray() {
        return Arrays.copyOf(buffer, offset);
    }

    /**
     * Creates {@code char} subarray from buffered content.
     */
    public char[] toArray(final int start, final int len) {
        final char[] array = new char[len];

        if (len == 0) {
            return array;
        }

        System.arraycopy(buffer, start, array, 0, len);

        return array;
    }

    /**
     * Returns {@code char} element at given index.
     */
    public char get(final int index) {
        if (index >= offset) {
            throw new IndexOutOfBoundsException();
        }
        return buffer[index];
    }

    /**
     * Appends character sequence to buffer.
     */
    @Override
    public FastCharBuffer append(final CharSequence csq) {
        append(csq, 0, csq.length());
        return this;
    }

    /**
     * Appends character sequence to buffer.
     */
    @Override
    public FastCharBuffer append(final CharSequence csq, final int start, final int end) {
        for (int i = start; i < end; i++) {
            append(csq.charAt(i));
        }
        return this;
    }

    @Override
    public char charAt(final int index) {
        return get(index);
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return new CharArraySequence(buffer, start, end - start);
    }

    /**
     * Returns a String of the char buffer.
     * Please use {@code StringBuilder} instead, it is faster.
     */
    @Override
    public String toString() {
        return new String(toArray());
    }
}
