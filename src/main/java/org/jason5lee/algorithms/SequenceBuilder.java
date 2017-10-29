package org.jason5lee.algorithms;

import java.util.Objects;
import java.util.function.Function;

/**
 * A builder class for creating a sequence,
 * with specified the first value and iterated function.
 * This sequence either has a specified length,
 * or uses a strategy to detect whether it
 * has a repeated element.
 *
 * @param <T> The type of the elements of the sequence.
 *
 * @author Jason Lee(&#x674e;&#x4e1c;&#x6052;)
 */
public class SequenceBuilder<T>{
    private T first;
    private Function<T, T> f;
    private final static int FLOYD = -1;
    private final static int HASH_MAP = -2;
    private int lengthOrCycleDetection;

    /**
     * Specify the first value of the sequence.
     * Without specification, the default first value is null.
     * @param first the first value.
     * @return the builder itself.
     */
    public SequenceBuilder<T> first(T first) {
        this.first = first;
        return this;
    }

    /**
     * Specify the iterated function.
     * @param f the iterated function
     * @return the builder itself.
     * @throws NullPointerException if the function is null.
     */
    public SequenceBuilder<T> iterateBy(Function<T, T> f) {
        Objects.requireNonNull(f);
        this.f = f;
        return this;
    }

    /**
     * Use floyd cycle detecting algorithm
     * to determined whether the sequence has repeated
     * values.
     * @return the builder itself.
     */
    public SequenceBuilder<T> floydCycleDetection() {
        lengthOrCycleDetection = FLOYD;
        return this;
    }

    /**
     * Use HashMap to detect whether the sequence
     * has repeated values.
     * @return the builder itself.
     */
    public SequenceBuilder<T> hashMapCycleDetection() {
        lengthOrCycleDetection = HASH_MAP;
        return this;
    }

    /**
     * Specify the length of the sequence.
     * If you neither specify the length
     * nor the cycle detection
     * the sequence is default empty.
     * @param length
     * @return the builder itself.
     * @throws IllegalArgumentException if length is negative.
     */
    public SequenceBuilder<T> length(int length) {
        if (length < 0)
            throw new IllegalArgumentException("The length shouldn't be negative.");

        lengthOrCycleDetection = length;
        return this;
    }

    /**
     * Build the sequences.
     * @return the {@code Iterable<T>} object representing the sequence.
     * @throws IllegalStateException if you never specify the iterated function.
     */
    public Iterable<T> build() {
        if (f == null)
            throw new IllegalStateException("No iterated function specified.");

        switch (lengthOrCycleDetection) {
            case FLOYD:
                return new FloydCycleDetection<>(first, f);
            case HASH_MAP:
                return new HashSetCycleDetection<>(first, f);
            default:
                assert lengthOrCycleDetection >= 0;
                return new FixLength<>(first, f, lengthOrCycleDetection);
        }
    }
}
