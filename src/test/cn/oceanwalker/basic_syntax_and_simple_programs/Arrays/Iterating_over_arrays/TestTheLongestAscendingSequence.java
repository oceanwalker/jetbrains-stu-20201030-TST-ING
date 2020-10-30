package test.cn.oceanwalker.basic_syntax_and_simple_programs.Arrays.Iterating_over_arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTheLongestAscendingSequence {
    @Test
    public void test() {
        assertEquals(5, count(new int[]{1, 2, 4, 1, 2, 3, 5, 7, 4, 3}));
        assertEquals(5, count(new int[]{1, 2, 4, 5, 4, 3, 1, 2, 3, 5, 7}));
    }

    private int count(int[] array) {
        int last = array[0];
        int longest = 1;
        int curLongest = 1;
        for (int i = 1; i < array.length; i++) {
            int cur = array[i];
            if (cur > last) {
                curLongest++;
            } else {
                if (curLongest > longest) {
                    longest = curLongest;
                }
                curLongest = 1;
            }
            last = cur;
        }
        if (curLongest > longest) {
            longest = curLongest;
        }
        return longest;
    }
}
