package org.jason5lee.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * A wrapper class containing an array and a comparator.
 * @author Jason Lee(&#x674e;&#x4e1c;&#x6052;)
 */
public class SortableArray<T> {
    private T[] array;
    private Comparator<T> comparator;

    public T[] getArray() {
        return array;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    private SortableArray(T[] array, Comparator<T> comparator) {
        assert array != null;
        assert comparator != null;

        this.array = array;
        this.comparator = comparator;
    }

    /**
     * Returns the wrapper containing the specified array and comparator.
     *
     * @param array the array of the wrapper.
     * @param comparator the comparator of the wrapper.
     * @param <U> the underlying type of the array.
     * @return the wrapper.
     * @throws NullPointerException if either array or comparator is null.
     */
    public static <U> SortableArray<U> of(U[] array, Comparator<U> comparator) {
        Objects.requireNonNull(array);
        Objects.requireNonNull(comparator);

        return new SortableArray<>(array, comparator);
    }

    /**
     * Returns the wrapper containing the specified array
     * of Comparable elements.
     * @param array the array of the wrapper.
     * @param <U> the type of the elements of array. It must implement {@code Comparable<U>}.
     * @return the wrapper.
     * @throws NullPointerException if the array is null.
     */
    public static <U extends Comparable<U>> SortableArray<U> of(U[] array) {
        Objects.requireNonNull(array);

        return new SortableArray<>(array, Comparator.naturalOrder());
    }

    /**
     * Sort the array.
     */
    public void sort() {
        Arrays.sort(array, comparator);
    }

    /**
     * Removes all but the first element
     * from every consecutive group of equal elements,
     * which is similar to the unique function in C++.
     * This method doesn't change the length of array
     * since it doesn't create any new array.
     * It only moves the elements.
     * @return The "real" length after removing.
     */
    public int unique() {
        int i, j;
        for (i = 0, j = 1; j < array.length; ++j)
            if (comparator.compare(array[i], array[j]) != 0)
                array[++i] = array[j];
        return i + 1;
    }

    /**
     * Searches a range of
     * the array for the specified object using the binary
     * search algorithm.
     * The range must be sorted into ascending order
     * according to the comparator (as by the
     * {@link #sort()} method) prior to making this call.
     * If it is not sorted, the results are undefined.
     * If the range contains multiple elements equal to the specified object,
     * there is no guarantee which one will be found.
     *
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or <tt>toIndex</tt> if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > this.toArray().length}
     */
    public int binarySearch(int fromIndex, int toIndex, T key) {
        return Arrays.binarySearch(array, fromIndex, toIndex, key, comparator);
    }

    /**
     * Returns an integer array a[] with the length of {@code array.length} that satisfies
     *   (1) 0 <= a[i] < the number of distinct values in {@code array}.
     *   (2) for each i, j that are in [0, {@code array.length}),
     *      {@code sgn(i.compareTo(j)) == sgn(comparator.compare(array[i], array[j]))}.
     * Note that this method doesn't change the original array.
     * @return the array a.
     */
    public int[] discretize() {
        SortableArray<T> unique = new SortableArray<>(array.clone(), comparator);
        unique.sort();

        int newLen = unique.unique();
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; ++i)
            result[i] = unique.binarySearch(0, newLen, array[i]);

        return result;
    }
}