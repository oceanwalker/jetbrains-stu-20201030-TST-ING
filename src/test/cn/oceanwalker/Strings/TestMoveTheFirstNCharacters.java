package test.cn.oceanwalker.Strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMoveTheFirstNCharacters {
    @Test
    public void test() {
        assertEquals("loHel", move("Hello", 3));
        assertEquals("Hello", move("Hello", 5));
        assertEquals("Hello", move("Hello", 6));
    }

    private String move(String raw, int i) {
        final int n = raw.length();
        if (i >= n) {
            return raw;
        }
        return raw.substring(i, n) + raw.substring(0, i);
    }
}
