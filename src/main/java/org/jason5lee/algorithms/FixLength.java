package org.jason5lee.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

// A iteration sequence with given length.
class FixLength<T> implements Iterable<T> {
    private final T startWith;
    private final Function<T, T> f;
    private int length;

    FixLength(T startWith, Function<T, T> f, int length) {
        assert f != null;
        assert length >= 0;

        this.length = length;
        this.startWith = startWith;
        this.f = f;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            T current = startWith;
            int rest = length;
            @Override
            public boolean hasNext() {
                return rest > 0;
            }

            @Override
            public T next() {
                if (rest > 0) {
                    T result = current;
                    current = f.apply(current);
                    --rest;
                    return result;
                }
                else
                    throw new NoSuchElementException();
            }
        };
    }
}
