package org.jason5lee.algorithms;

import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

/**
 * An utility class including some functions that
 * are relative with binary search algorithm.
 *
 * @author Jason Lee(&#x674e;&#x4e1c;&#x6052;)
 */
public final class BinarySearch {
    // Suppresses default constructor, ennsuring non-instantiability.
    private BinarySearch() {
        throw new AssertionError();
    }

    /**
     * Find the least integer n in the half-open interval [l,r)
     * such that f(n) is true.
     * The predicate f must be increasing in the range, which means,
     * for all {@literal a,b in [l,r), a > b}, if f(b) is true, f(a) should also be true.
     * If f is NOT increasing, the result is undefined.
     * @param l the left endpoint of the interval (inclusive).
     * @param r the right endpoint of the interval (exclusive).
     * @param f the predicate function.
     * @return the value of n if {@code l < r} and it exists.
     *         other wise, return the right endpoint r.
     */
    public static int findLeastTrue(int l, int r, IntPredicate f) {
        Objects.requireNonNull(f);

        while (l < r) {
            int mid = (l + r) >>> 1;
            if (f.test(mid))
                r = mid;
            else
                l = mid + 1;
        }

        return r;
    }

    /**
     * Find the least real number x in the half-open interval [l,r)
     * such that f(x) is true.
     * The predicate f must be increasing in the range, which means,
     * for all {@literal a,b in [l,r), a > b}, if f(b) is true, f(a) should also be true.
     * If f is NOT increasing, the result is undefined.
     * @param l the left endpoint of the interval (inclusive).
     * @param r the right endpoint of the interval (exclusive).
     * @param f the predicate function.
     * @param eps the precision of the result. eps may be zero but CANNOT be negative.
     * @return the value of x if {@code l < r - eps} and it exists.
     *         other wise, return the right endpoint r.
     */
    public static double findLeastTrue(double l, double r, DoublePredicate f, double eps) {
        Objects.requireNonNull(f);
        if (!(eps >= 0))
            throw new IllegalArgumentException("eps should be a positive number.");

        if (l != l)
            throw new IllegalArgumentException("l should not be NaN.");

        if (r != r)
            throw new IllegalArgumentException("r should not be NaN.");

        if (r - l == Double.POSITIVE_INFINITY)
            throw new IllegalArgumentException("The range has infinity length.");

        while (l < r - eps) {
            double mid = l + (r - l) / 2;
            if (l == mid || r == mid)
                break;
            if (f.test(mid))
                r = mid;
            else
                l = mid;
        }

        return r;
    }
}
