package io.nebula;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public class CharArraySequence implements CharSequence {

    public static final CharSequence EMPTY = CharArraySequence.of();

    private final char[] buffer;
    private final int off, len;

    public CharArraySequence(final char[] value) {
        buffer = value;
        off = 0;
        len = value.length;
    }

    public CharArraySequence(final char[] value, final int offset, final int length) {
        if ((offset | length | offset + length | value.length - offset - length) < 0) {
            throw new IndexOutOfBoundsException();
        }
        buffer = value;
        off = offset;
        len = length;
    }

    /**
     * Ctor without the bounds check.
     */
    private CharArraySequence(final int offset, final int length, final char[] value) {
        off = offset;
        len = length;
        buffer = value;
    }

    /**
     * Static constructor that creates a char sequence using provided char array.
     */
    public static CharArraySequence of(final char... value) {
        return new CharArraySequence(value);
    }

    public static CharArraySequence of(final char[] value, final int offset, final int len) {
        return new CharArraySequence(value, offset, len);
    }

    /**
     * Static constructor that creates a char sequence by making a copy of provided char array.
     */
    public static CharArraySequence from(final char[] value, final int offset, final int len) {
        final char[] buffer = new char[value.length];

        System.arraycopy(value, offset, buffer, 0, len);

        return new CharArraySequence(buffer);
    }

    @Override
    public int length() {
        return len;
    }

    @Override
    public String toString() {
        return String.valueOf(buffer, off, len);
    }

    @Override
    public char charAt(final int index) {
        //if ((index | len - index - 1) < 0) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return buffer[off + index];
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        final int count = end - start;
        final int rem = len - end;

        if ((start | end | count | rem) < 0) {
            throw new IndexOutOfBoundsException();
        }
        if ((start | rem) == 0) {
            return this;
        }

        return new CharArraySequence(off + start, count, buffer);
    }

}
