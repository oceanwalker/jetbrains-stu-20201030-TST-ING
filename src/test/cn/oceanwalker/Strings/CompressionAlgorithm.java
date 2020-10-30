package test.cn.oceanwalker.Strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompressionAlgorithm {
    @Test
    public void test() {
        assertEquals("a4b2c1a2", compression("aaaabbcaa"));
        assertEquals("a1b1c1", compression("abc"));
        assertEquals("a5", compression("aaaaa"));
    }

    @Test
    public void test1() {
        assertEquals("a4b2c1a2", compression1("aaaabbcaa"));
        assertEquals("a1b1c1", compression1("abc"));
        assertEquals("a5", compression1("aaaaa"));
    }

    private String compression1(String raw) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        final int n = raw.length();
        while (i < n) {
            int shfit = 1;
            while (i + shfit < n && raw.charAt(i) == raw.charAt(i + shfit)) {
                shfit++;
            }
            result.append(raw.charAt(i)).append(shfit);
            i += shfit;
        }
        return result.toString();
    }

    private String compression(String raw) {
        StringBuilder result = new StringBuilder();
        int n = raw.length();
        char temp = raw.charAt(0);
        int count = 1;
        for (int i = 1; i < n; i++) {
            final char c = raw.charAt(i);
            if (temp == c) {
                count++;
            } else {
                result.append(temp).append(count);
                count = 1;
                temp = c;
            }
        }
        result.append(temp).append(count);
        return result.toString();
    }
}
