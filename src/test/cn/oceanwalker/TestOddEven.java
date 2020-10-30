package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestOddEven {
    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4, 0};
        String result = "odd " +
                "even " +
                "odd " +
                "even ";
        assertEquals(result, judge(a));
    }

    private String judge(int[] a) {
        StringBuilder result = new StringBuilder();
        for (int i : a) {
            if (i == 0) {
                break;
            }
            if (i % 2 == 0) {
                result.append("even ");
            } else {
                result.append("odd ");
            }
        }
        return result.toString();
    }
}
