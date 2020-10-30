package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestQueenHit {
    @Test
    public void test() {
        assertEquals(true, judge(1, 1, 3, 3));
        assertEquals(false, judge(1, 1, 4, 3));
        assertEquals(true, judge(2, 2, 5, 2));
    }

    private boolean judge(int x1, int y1, int x2, int y2) {
        boolean isXHit = x1 == x2;
        boolean isYHit = y1 == y2;
        boolean isDHit = Math.abs(x1 - x2) == Math.abs(y1 - y2);
        return isDHit || isXHit || isYHit;
    }
}
