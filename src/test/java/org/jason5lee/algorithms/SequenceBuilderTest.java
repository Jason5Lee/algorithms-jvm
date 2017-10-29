package org.jason5lee.algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;


public class SequenceBuilderTest{
    @Test(expected = NullPointerException.class)
    public void testNullIteratedFunction() {
        new SequenceBuilder<Integer>().iterateBy(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoIteratedFunction() {
        new SequenceBuilder<Object>().build();
    }

    @Test
    public void testEmpty() {
        Iterable<Object> seq = new SequenceBuilder<Object>()
                .iterateBy(x -> x)
                .build();
        Assert.assertFalse(seq.iterator().hasNext());
    }

    @Test
    public void testFloydCycleDetection() {
        Iterable<Integer> seq = new SequenceBuilder<Integer>()
                .first(1)
                .iterateBy(x -> x * 3 % 7)
                .floydCycleDetection()
                .build();

        Iterator<Integer> itor = seq.iterator();

        Assert.assertEquals((Integer)1, itor.next());
        Assert.assertEquals((Integer)3, itor.next());
        Assert.assertEquals((Integer)2, itor.next());
        Assert.assertEquals((Integer)6, itor.next());
        Assert.assertEquals((Integer)4, itor.next());
        Assert.assertEquals((Integer)5, itor.next());

        while (itor.hasNext()) {
            itor.next();
        }
    }

    @Test
    public void testFixLength() {
        Iterable<Integer> seq = new SequenceBuilder<Integer>()
                .first(1)
                .iterateBy(x -> x * 7 % 10)
                .length(5)
                .build();

        Iterator<Integer> itor = seq.iterator();
        Assert.assertEquals((Integer)1, itor.next());
        Assert.assertEquals((Integer)7, itor.next());
        Assert.assertEquals((Integer)9, itor.next());
        Assert.assertEquals((Integer)3, itor.next());
        Assert.assertEquals((Integer)1, itor.next());
        Assert.assertFalse(itor.hasNext());
    }

    @Test
    public void testHashMapCycleDetection() {
        Iterable<Integer> seq = new SequenceBuilder<Integer>()
                .first(1)
                .iterateBy(x -> x * 3 % 7)
                .hashMapCycleDetection()
                .build();

        Iterator<Integer> itor = seq.iterator();
        Assert.assertEquals((Integer)1, itor.next());
        Assert.assertEquals((Integer)3, itor.next());
        Assert.assertEquals((Integer)2, itor.next());
        Assert.assertEquals((Integer)6, itor.next());
        Assert.assertEquals((Integer)4, itor.next());
        Assert.assertEquals((Integer)5, itor.next());
        Assert.assertFalse(itor.hasNext());
    }
}
