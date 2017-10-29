package org.jason5lee.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

// A iteration sequence which stops when repeated element is
// detected by floyd cycle detection.
class FloydCycleDetection<T> implements Iterable<T> {
    private final T startWith;
    private final Function<T, T> iteratedFunction;

    FloydCycleDetection(T startWith, Function<T, T> iteratedFunction) {
        assert iteratedFunction != null;
        this.startWith = startWith;
        this.iteratedFunction = iteratedFunction;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            T current = startWith;
            T halfPace = startWith;

            private int status;

            @Override
            public boolean hasNext() {
                return status != -1;
            }

            @Override
            public T next() {
                T result = current;
                switch (status) {
                    case 0:
                        current = iteratedFunction.apply(current);
                        status = 1;
                        break;
                    case 1:
                        current = iteratedFunction.apply(current);
                        halfPace = iteratedFunction.apply(halfPace);
                        status = current.equals(halfPace) ? -1 : 0;
                        break;
                    default:
                        throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}
