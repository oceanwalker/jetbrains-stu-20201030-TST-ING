package test.cn.oceanwalker.basic_syntax_and_simple_programs.Arrays.Iterating_over_arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlphabeticalOrder {
    @Test
    public void test() {
        assertEquals("true", judge("a b c"));
        assertEquals("true", judge("a aa az aza"));
    }

    private String judge(String str) {
        String[] array = str.split(" ");
        for (int i = 1; i < array.length; i++) {
            if (!inOrder(array[i - 1], array[i])) {
                return "false";
            }
        }
        return "true";
    }

    private boolean inOrder(String a, String b) {
        return a.compareTo(b) <= 0 ? true : false;
    }
}
