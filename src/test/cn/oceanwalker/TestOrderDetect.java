package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestOrderDetect {
    @Test
    public void test() {
        int[] a1 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] a2 = new int[]{1, 2, 3, 3, 9, 0};
        int[] a3 = new int[]{1, 2, 5, 5, 2, 3, 0};
        assertEquals(true, orderDetect(a1));
        assertEquals(true, orderDetect(a2));
        assertEquals(false, orderDetect(a3));
    }

    private boolean orderDetect(int[] a) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean isDesc = true;
        boolean isAsc = true;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                break;
            }
            if (isDesc) {
                if (a[i] <= min) {
                    min = a[i];
                } else {
                    isDesc = false;
                }
            }
            if (isAsc) {
                if (a[i] >= max) {
                    max = a[i];
                } else {
                    isAsc = false;
                }
            }
        }
        return isAsc || isDesc;
    }
}
