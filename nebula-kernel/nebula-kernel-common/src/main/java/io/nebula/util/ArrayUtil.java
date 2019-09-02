package io.nebula.util;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/25
 */
public class ArrayUtil {
    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(Object[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = StringUtil.toString(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(String[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(byte[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(char[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(short[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(int[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(long[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(float[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(double[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    /**
     * Converts an array to string array.
     */
    public static String[] toStringArray(boolean[] array) {
        if (array == null) {
            return null;
        }
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = String.valueOf(array[i]);
        }
        return result;
    }

    // ---------------------------------------------------------------- indexof


    /**
     * Finds the first occurrence of an element in an array.
     */
    public static int indexOf(byte[] array, byte value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if an array contains given value.
     */
    public static boolean contains(byte[] array, byte value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in an array from specified given position.
     */
    public static int indexOf(byte[] array, byte value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(byte[] array, byte value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of an element in an array.
     */
    public static int indexOf(char[] array, char value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if an array contains given value.
     */
    public static boolean contains(char[] array, char value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in an array from specified given position.
     */
    public static int indexOf(char[] array, char value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(char[] array, char value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of an element in an array.
     */
    public static int indexOf(short[] array, short value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if an array contains given value.
     */
    public static boolean contains(short[] array, short value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in an array from specified given position.
     */
    public static int indexOf(short[] array, short value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(short[] array, short value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of an element in an array.
     */
    public static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if an array contains given value.
     */
    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in an array from specified given position.
     */
    public static int indexOf(int[] array, int value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(int[] array, int value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of an element in an array.
     */
    public static int indexOf(long[] array, long value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if an array contains given value.
     */
    public static boolean contains(long[] array, long value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in an array from specified given position.
     */
    public static int indexOf(long[] array, long value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(long[] array, long value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of an element in an array.
     */
    public static int indexOf(boolean[] array, boolean value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if an array contains given value.
     */
    public static boolean contains(boolean[] array, boolean value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in an array from specified given position.
     */
    public static int indexOf(boolean[] array, boolean value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(boolean[] array, boolean value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of value in <code>float</code> array.
     */
    public static int indexOf(float[] array, float value) {
        for (int i = 0; i < array.length; i++) {
            if (Float.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if <code>float</code> array contains given value.
     */
    public static boolean contains(float[] array, float value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in <code>float</code>
     * array from specified given position.
     */
    public static int indexOf(float[] array, float value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (Float.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in <code>float</code> array from specified given position and upto given length.
     */
    public static int indexOf(float[] array, float value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (Float.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence of value in <code>double</code> array.
     */
    public static int indexOf(double[] array, double value) {
        for (int i = 0; i < array.length; i++) {
            if (Double.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns <code>true</code> if <code>double</code> array contains given value.
     */
    public static boolean contains(double[] array, double value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence of given value in <code>double</code>
     * array from specified given position.
     */
    public static int indexOf(double[] array, double value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (Double.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in <code>double</code> array from specified given position and upto given length.
     */
    public static int indexOf(double[] array, double value, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            if (Double.compare(array[i], value) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(Object[] array, Object value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean contains(Object[] array, Object value) {
        return indexOf(array, value) != -1;
    }

    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(Object[] array, Object value, int startIndex) {
        for (int i = startIndex; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean contains(Object[] array, Object value, int startIndex) {
        return indexOf(array, value, startIndex) != -1;
    }


    // ---------------------------------------------------------------- indexof 2


    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(byte[] array, byte[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(byte[] array, byte[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(byte[] array, byte[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(byte[] array, byte[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        byte c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (array[i] != c) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (sub[j] != array[k]) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(char[] array, char[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(char[] array, char[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(char[] array, char[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(char[] array, char[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        char c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (array[i] != c) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (sub[j] != array[k]) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(short[] array, short[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(short[] array, short[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(short[] array, short[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(short[] array, short[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        short c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (array[i] != c) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (sub[j] != array[k]) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(int[] array, int[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(int[] array, int[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(int[] array, int[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(int[] array, int[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        int c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (array[i] != c) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (sub[j] != array[k]) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(long[] array, long[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(long[] array, long[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(long[] array, long[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(long[] array, long[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        long c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (array[i] != c) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (sub[j] != array[k]) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(boolean[] array, boolean[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(boolean[] array, boolean[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(boolean[] array, boolean[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(boolean[] array, boolean[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        boolean c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (array[i] != c) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (sub[j] != array[k]) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(float[] array, float[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(float[] array, float[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(float[] array, float[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(float[] array, float[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        float c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (Float.compare(array[i], c) != 0) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (Float.compare(sub[j], array[k]) != 0) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }

    /**
     * Finds the first occurrence in an array.
     */
    public static int indexOf(double[] array, double[] sub) {
        return indexOf(array, sub, 0, array.length);
    }

    public static boolean contains(double[] array, double[] sub) {
        return indexOf(array, sub) != -1;
    }


    /**
     * Finds the first occurrence in an array from specified given position.
     */
    public static int indexOf(double[] array, double[] sub, int startIndex) {
        return indexOf(array, sub, startIndex, array.length);
    }

    /**
     * Finds the first occurrence in an array from specified given position and upto given length.
     */
    public static int indexOf(double[] array, double[] sub, int startIndex, int endIndex) {
        int sublen = sub.length;
        if (sublen == 0) {
            return startIndex;
        }
        int total = endIndex - sublen + 1;
        double c = sub[0];
        mainloop:
        for (int i = startIndex; i < total; i++) {
            if (Double.compare(array[i], c) != 0) {
                continue;
            }
            int j = 1;
            int k = i + 1;
            while (j < sublen) {
                if (Double.compare(sub[j], array[k]) != 0) {
                    continue mainloop;
                }
                j++;
                k++;
            }
            return i;
        }
        return -1;
    }
}
