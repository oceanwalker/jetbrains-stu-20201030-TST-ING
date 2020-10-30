package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFindMaxDivisible {
    @Test
    public void test() {
        int[] a = new int[]{12, 16, 87, 58, 25, 73, 86, 36, 79, 40, 12, 89, 32};
        assertEquals(40, findMaxDivisible(a, 4));
    }

    private int findMaxDivisible(int[] a, int divisor) {
        int max = Integer.MIN_VALUE;
        for (int i : a) {
            if (i % 4 == 0) {
                if (i > max) {
                    max = i;
                }
            }
        }
        return max;
    }
}
