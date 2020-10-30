package test.cn.oceanwalker.basic_syntax_and_simple_programs.Arrays.Iterating_over_arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSumArrayElementsGreaterThanAValue {
    @Test
    public void test() {
        assertEquals(21, sumArray(new int[]{5, 8, 11, 2, 10}, 8));
        assertEquals(-15, sumArray(new int[]{-5, -4, -6, -11}, -10));
    }

    private int sumArray(int[] array, int target) {
        int result=0;
        for (int a : array) {
            if(a>target){
                result+=a;
            }
        }
        return result;
    }
}
