package test.cn.oceanwalker.java.collections.iterator_and_iterable;

import java.util.Iterator;

class Range implements Iterable<Long> {

    private long fromInclusive;
    private long toExclusive;

    public Range(long from, long to) {
        this.fromInclusive = from;
        this.toExclusive = to;
    }

    @Override
    public Iterator<Long> iterator() {
        // write your code here
        return new Iterator<Long>() {
            private long index = fromInclusive;

            @Override
            public boolean hasNext() {
                return index < toExclusive;
            }

            @Override
            public Long next() {
                return index++;
            }
        };
    }
}
