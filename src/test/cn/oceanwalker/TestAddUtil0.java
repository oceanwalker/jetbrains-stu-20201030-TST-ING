package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAddUtil0 {
    @Test
    public void test() {
        int[] a = new int[]{3, 6, 8, 0};
        assertEquals(17, add(a));
    }

    private int add(int[] a) {
        int result = 0;
        for (int i : a) {
            if (i == 0) {
                break;
            }
            result += i;
        }
        return result;
    }
}
