package io.nebula.buffer;

import java.util.Arrays;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class FastByteBuffer {

    private byte[] buffer;
    private int offset;

    /**
     * Creates a new {@code byte} buffer. The buffer capacity is
     * initially 64 bytes, though its size increases if necessary.
     */
    public FastByteBuffer() {
        this.buffer = new byte[64];
    }

    /**
     * Creates a new {@code byte} buffer, with a buffer capacity of
     * the specified size.
     *
     * @param size the initial size.
     * @throws IllegalArgumentException if size is negative.
     */
    public FastByteBuffer(final int size) {
        this.buffer = new byte[size];
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
     * Appends single {@code byte} to buffer.
     */
    public void append(final byte element) {
        if (offset - buffer.length >= 0) {
            grow(offset);
        }

        buffer[offset++] = element;
    }

    /**
     * Appends {@code byte} array to buffer.
     */
    public FastByteBuffer append(final byte[] array, final int off, final int len) {
        if (offset + len - buffer.length > 0) {
            grow(offset + len);
        }

        System.arraycopy(array, off, buffer, offset, len);
        offset += len;
        return this;
    }

    /**
     * Appends {@code byte} array to buffer.
     */
    public FastByteBuffer append(final byte[] array) {
        return append(array, 0, array.length);
    }

    /**
     * Appends another fast buffer to this one.
     */
    public FastByteBuffer append(final FastByteBuffer buff) {
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
     * Creates {@code byte} array from buffered content.
     */
    public byte[] toArray() {
        return Arrays.copyOf(buffer, offset);
    }

    /**
     * Creates {@code byte} subarray from buffered content.
     */
    public byte[] toArray(final int start, final int len) {
        final byte[] array = new byte[len];

        if (len == 0) {
            return array;
        }

        System.arraycopy(buffer, start, array, 0, len);

        return array;
    }

    /**
     * Returns {@code byte} element at given index.
     */
    public byte get(final int index) {
        if (index >= offset) {
            throw new IndexOutOfBoundsException();
        }
        return buffer[index];
    }

}
