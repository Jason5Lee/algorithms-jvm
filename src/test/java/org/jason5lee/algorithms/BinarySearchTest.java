package org.jason5lee.algorithms;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple OrderableArray.
 */
public class BinarySearchTest  {
    private int intSqrt(int x) {
        return BinarySearch.findLeastTrue(0, x, (int n) -> n*n >= x);
    }

    private double doubleSqrt(double x) {
        return BinarySearch.findLeastTrue(0, x, n -> n*n >= x, 1e-7);
    }

    @Test(expected = NullPointerException.class)
    public void testArgCheck1() {
        BinarySearch.findLeastTrue(0, 0, null);
    }

    @Test(expected = NullPointerException.class)
    public void testArgCheck2() {
        BinarySearch.findLeastTrue(0, 0, null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgCheck3() {
        BinarySearch.findLeastTrue(-5, 3, x -> x > 0, -1e-7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNCheck1() { BinarySearch.findLeastTrue(Double.NaN, 3, x -> x > 0, -1e-7); }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNCheck2() { BinarySearch.findLeastTrue(0, Double.NaN, x -> x > 0, -1e-7); }

    @Test(expected = IllegalArgumentException.class)
    public void testNaNCheck3() { BinarySearch.findLeastTrue(0, 3, x -> x > 0, Double.NaN); }

    @Test(expected = IllegalArgumentException.class)
    public void testInftyRangeCheck1() { BinarySearch.findLeastTrue(Double.NEGATIVE_INFINITY, 3, x -> x > 0, -1e-7); }

    @Test(expected = IllegalArgumentException.class)
    public void testInftyRangeCheck2() { BinarySearch.findLeastTrue(0, Double.POSITIVE_INFINITY, x -> x > 0, -1e-7); }

    @Test(expected = IllegalArgumentException.class)
    public void testInftyRangeCheck3() { BinarySearch.findLeastTrue(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, x -> x > 0, -1e-7); }

    @Test
    public void testSpecialCases() {
        Assert.assertEquals(BinarySearch.findLeastTrue(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, x -> true, 1e-7), Double.NEGATIVE_INFINITY, 1e-7);
        Assert.assertEquals(BinarySearch.findLeastTrue(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, x -> true, 1e-7), Double.POSITIVE_INFINITY, 1e-7);
    }

    @Test
    public void testInt() {
        Assert.assertEquals(10, intSqrt(100));
        Assert.assertEquals(11, intSqrt(101));
        Assert.assertEquals(-3, intSqrt(-3));
    }

    @Test
    public void testDouble() {
        Assert.assertEquals(Math.sqrt(2), doubleSqrt(2), 1e-7);
        Assert.assertEquals(Math.sqrt(5), doubleSqrt(5), 1e-7);
        Assert.assertEquals(Math.sqrt(1000), doubleSqrt(1000), 1e-7);
        Assert.assertEquals(-Math.PI, doubleSqrt(-Math.PI), 1e-7);
        Assert.assertTrue(true);
    }
}
