package org.jason5lee.algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

// A iteration sequence which stops when repeated element is
// detected using HashMap.
class HashSetCycleDetection<T> implements Iterable<T> {
    private final Function<T, T> iteratedFunction;
    private final T init;

    HashSetCycleDetection(T init, Function<T, T> iteratedFunction) {
        assert iteratedFunction != null;
        this.iteratedFunction = iteratedFunction;
        this.init = init;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            T current = init;
            HashSet<T> hashSet = new HashSet<>();

            @Override
            public boolean hasNext() {
                return !hashSet.contains(current);
            }

            @Override
            public T next() {
                if (!hashSet.add(current))
                    throw new NoSuchElementException();

                T result = current;
                current = iteratedFunction.apply(current);
                return result;
            }
        };
    }
}
