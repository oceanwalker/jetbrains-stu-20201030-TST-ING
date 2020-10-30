package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBarrier {
    @Test
    public void test() {
        int[] a = new int[]{800, 101, 102, 300, 0};
        assertEquals(3, barrier(a));
        a = new int[]{103, 105, 109, 0, 1000};
        assertEquals(317, barrier(a));
    }

    private int barrier(int[] a) {
        int sum = 0;
        int i = 0;
        while (sum < 1000 && a[i] != 0) {
            sum += a[i];
            i++;
            if (i == a.length) {
                break;
            }
        }
        return sum >= 1000 ? sum - 1000 : sum;
    }
}
