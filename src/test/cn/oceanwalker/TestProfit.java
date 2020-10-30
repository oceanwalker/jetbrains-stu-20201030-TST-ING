package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProfit {
    @Test
    public void test() {
        assertEquals(3, profit(1, 100, 8));
        assertEquals(2, profit(100, 15, 120));
    }

    private int profit(int m, int p, int k) {
        int year = 0;
        if (m >= k) {
            return year;
        }
        while (m < k) {
            m += m * p * 0.01;
            year++;
        }
        return year;
    }
}
