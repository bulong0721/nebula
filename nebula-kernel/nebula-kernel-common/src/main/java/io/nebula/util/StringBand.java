package io.nebula.util;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/9/3
 */
public class StringBand {

    private static final int DEFAULT_ARRAY_CAPACITY = 16;

    private String[] array;
    private int index;
    private int length;

    /**
     * Creates an empty <code>StringBand</code>.
     */
    public StringBand() {
        array = new String[DEFAULT_ARRAY_CAPACITY];
    }

    /**
     * Creates an empty <code>StringBand</code> with provided capacity.
     * Capacity refers to internal string array (i.e. number of
     * joins) and not the total string size.
     */
    public StringBand(final int initialCapacity) {
        array = new String[initialCapacity];
    }

    /**
     * Creates <code>StringBand</code> with provided content.
     */
    public StringBand(final String s) {
        this();
        array[0] = s;
        index = 1;
        length = s.length();
    }

    public StringBand(final Object o) {
        this(String.valueOf(o));
    }

    // ---------------------------------------------------------------- append

    /**
     * Appends boolean value.
     */
    public StringBand append(final boolean b) {
        return append(b ? StringPool.TRUE : StringPool.FALSE);
    }

    /**
     * Appends double value.
     */
    public StringBand append(final double d) {
        return append(Double.toString(d));
    }

    /**
     * Appends float value.
     */
    public StringBand append(final float f) {
        return append(Float.toString(f));
    }

    /**
     * Appends int value.
     */
    public StringBand append(final int i) {
        return append(Integer.toString(i));
    }

    /**
     * Appends long value.
     */
    public StringBand append(final long l) {
        return append(Long.toString(l));
    }

    /**
     * Appends short value.
     */
    public StringBand append(final short s) {
        return append(Short.toString(s));
    }

    /**
     * Appends a character. This is <b>not</b> efficient
     * as in <code>StringBuilder</code>, since new string is created.
     */
    public StringBand append(final char c) {
        return append(String.valueOf(c));
    }

    /**
     * Appends byte value.
     */
    public StringBand append(final byte b) {
        return append(Byte.toString(b));
    }

    /**
     * Appends string representation of an object.
     * If <code>null</code>, the <code>'null'</code> string
     * will be appended.
     */
    public StringBand append(final Object obj) {
        return append(String.valueOf(obj));
    }

    /**
     * Appends a string.
     */
    public StringBand append(String s) {
        if (s == null) {
            s = StringPool.NULL;
        }

        if (index >= array.length) {
            expandCapacity();
        }

        array[index++] = s;
        length += s.length();

        return this;
    }

    // ---------------------------------------------------------------- size

    /**
     * Returns array capacity.
     */
    public int capacity() {
        return array.length;
    }

    /**
     * Returns total string length.
     */
    public int length() {
        return length;
    }

    /**
     * Returns current index of string array.
     */
    public int index() {
        return index;
    }

    /**
     * Specifies the new index.
     */
    public void setIndex(final int newIndex) {
        if (newIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(newIndex);
        }

        if (newIndex > array.length) {
            String[] newArray = new String[newIndex];
            System.arraycopy(array, 0, newArray, 0, index);
            array = newArray;
        }

        if (newIndex > index) {
            for (int i = index; i < newIndex; i++) {
                array[i] = StringPool.EMPTY;
            }
        } else if (newIndex < index) {
            for (int i = newIndex; i < index; i++) {
                array[i] = null;
            }
        }

        index = newIndex;
        length = calculateLength();
    }

    // ---------------------------------------------------------------- values

    /**
     * Returns char at given position.
     * This method is <b>not</b> fast as it calculates
     * the right string array element and the offset!
     */
    public char charAt(final int pos) {
        int len = 0;
        for (int i = 0; i < index; i++) {
            int newlen = len + array[i].length();
            if (pos < newlen) {
                return array[i].charAt(pos - len);
            }
            len = newlen;
        }
        throw new IllegalArgumentException("Invalid char index");
    }

    /**
     * Returns string at given position.
     */
    public String stringAt(final int index) {
        if (index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    /**
     * Joins together all strings into one.
     */
    @Override
    public String toString() {

        // special cases
        switch (index) {
            case 0:
                return StringPool.EMPTY;
            case 1:
                return array[0];
            case 2:
                return array[0] + array[1];
            default:
                break;
        }

        // join strings
        char[] destination = new char[length];
        int start = 0;
        for (int i = 0; i < index; i++) {
            String s = array[i];

            int len = s.length();
            s.getChars(0, len, destination, start);

            start += len;
        }

        return new String(destination);
    }

    // ---------------------------------------------------------------- utils

    /**
     * Expands internal string array by multiplying its size by 2.
     */
    protected void expandCapacity() {
        String[] newArray = new String[array.length << 1];
        System.arraycopy(array, 0, newArray, 0, index);
        array = newArray;
    }

    /**
     * Calculates string length.
     */
    protected int calculateLength() {
        int len = 0;
        for (int i = 0; i < index; i++) {
            len += array[i].length();
        }
        return len;
    }

}
