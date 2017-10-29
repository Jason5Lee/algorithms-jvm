package org.jason5lee.algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

/**
 * Unit test for SortableArray.
 */
public class SortableArrayTest {
    @Test
	public void testNull() {
	    try {
	        SortableArray.of(null);
	        Assert.fail();
        }
        catch (NullPointerException ignored) {}

        try {
            SortableArray.of(new Integer[2], null);
            Assert.fail();
        }
        catch (NullPointerException ignored) {}

		try {
			SortableArray.<Integer>of(null, Comparator.naturalOrder());
			Assert.fail();
		}
		catch (NullPointerException ignored) {}
	}

	@Test
	public void testSort() {
	    Integer[] arr = new Integer[] { 2, 4, 5, 3, 9 ,7, 1};
	    SortableArray<Integer> acw = SortableArray.of(arr);
	    acw.sort();
	    Assert.assertEquals((Object)arr, acw.getArray());
	    Assert.assertArrayEquals(arr, new Integer[] {1, 2, 3, 4, 5, 7, 9});
	    acw = SortableArray.of(arr, Comparator.reverseOrder());
	    acw.sort();
        Assert.assertEquals((Object)arr, acw.getArray());
        Assert.assertArrayEquals(arr, new Integer[] {9, 7, 5, 4, 3, 2, 1});
    }

    @Test
    public void testUnique() {
        String[] arr = new String[] { "a", "a", "ab", "ab", "ab", "c", "d", "c", "e"};
        SortableArray<String> acw = SortableArray.of(arr);
        Assert.assertEquals(6, acw.unique());
        Assert.assertEquals((Object)arr, acw.getArray());
        Assert.assertArrayEquals(new String[]{ "a", "ab", "c", "d", "c", "e", "d", "c", "e"}, arr);
    }

    @Test
    public void testBinarySearch() {
        Double[] arr = new Double[] {1.0, 2.0, 3.1, 4.4, 5.2, 7.3, 9.9};
        SortableArray<Double> acw = SortableArray.of(arr);
        Assert.assertEquals(3, acw.binarySearch(0, 7, 4.4));
        Assert.assertEquals(-4, acw.binarySearch(0, 3, 100.0));
        Assert.assertEquals(-4, acw.binarySearch(3, 7, 0.0));

        try {
            acw.binarySearch(4, 3, 2.2);
            Assert.fail();
        }
        catch (IllegalArgumentException ignored) {}

        try {
            acw.binarySearch(-1, 3, 2.2);
            Assert.fail();
        }
        catch (ArrayIndexOutOfBoundsException ignored) {}

        try {
            acw.binarySearch(0, 8, 2.2);
            Assert.fail();
        }
        catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    @Test
    public void testDiscretize() {
        Double[] arr = new Double[] {1.1, Math.PI, Math.E, 2.0, 5.0, 1.1, 1.7, 2.0, Math.PI};
        // Sorted: 1.1 1.7 2.0 Math.E Math.PI 5.0
        SortableArray<Double> acw = SortableArray.of(arr);
        int[] dis = acw.discretize();

        Assert.assertEquals((Object)arr, acw.getArray());
        Assert.assertArrayEquals(new int[] {0, 4, 3, 2, 5, 0, 1, 2, 4}, dis);
    }
}
