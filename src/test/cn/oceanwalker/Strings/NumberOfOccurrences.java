package test.cn.oceanwalker.Strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberOfOccurrences {
    @Test
    public void test() {
        assertEquals(1, number("hello there", "the"));
        assertEquals(3, number("hello yellow jello", "ll"));
        assertEquals(1, number("ababa", "aba"));
    }

    @Test
    public void test2() {
        assertEquals(1, number2("hello there", "the"));
        assertEquals(3, number2("hello yellow jello", "ll"));
        assertEquals(1, number2("ababa", "aba"));
    }
    @Test
    public void test3() {
        assertEquals(1, number3("hello there", "the"));
        assertEquals(3, number3("hello yellow jello", "ll"));
        assertEquals(1, number3("ababa", "aba"));
    }

    private int number3(String s, String f) {
        return s.split(f).length-1;
    }

    private int number2(String s, String f) {
        int i = 0;
        int count = 0;
        while (i < s.length()) {
            if (s.indexOf(f, i) == i) {
                count++;
                i += f.length() - 1;
            }
            i++;
        }
        return count;
    }

    private int number(String str, String find) {
        int result = 0;
        int fl = find.length();

        final int sl = str.length();
        for (int i = 0; i <= sl - fl; i++) {
            boolean isMatch = true;
            for (int j = 0; j < fl; j++) {
                if (str.charAt(i + j) != find.charAt(j)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                result++;
                i += fl - 1;
            }
        }
        return result;
    }
}
