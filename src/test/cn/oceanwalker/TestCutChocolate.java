package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCutChocolate {
    @Test
    public void test() {
        assertEquals(true, cut(4, 2, 6));
        assertEquals(false, cut(2, 10, 7));
        assertEquals(true, cut(7, 4, 21));
    }

    private boolean cut(int m, int n, int k) {
        boolean result = false;
        final boolean isNOk = k % n == 0 && k / n < m;
        final boolean isMOk = k % m == 0 && k / m < n;
        return isMOk || isNOk;
    }
}
