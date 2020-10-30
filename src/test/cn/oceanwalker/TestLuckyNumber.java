package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLuckyNumber {
    @Test
    public void test() {
        assertEquals(true, lucky("090234"));
        assertEquals(false, lucky("123456"));
    }

    private boolean lucky(String s) {
        int front = 0;
        for (int i = 0; i < 3; i++) {
            front += Character.valueOf(s.charAt(i));
        }
        int end = 0;
        for (int i = s.length() - 1; i > s.length() - 1 - 3; i--) {
            end += Character.valueOf(s.charAt(i));
        }
        return front == end;
    }
}
