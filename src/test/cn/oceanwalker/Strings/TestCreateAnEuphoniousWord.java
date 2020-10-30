package test.cn.oceanwalker.Strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCreateAnEuphoniousWord {
    @Test
    public void test() {
        assertEquals(1, create("schedule"));
        assertEquals(0, create("garage"));
        assertEquals(1, create("player"));
        assertEquals(2, create("biiiiig"));
    }

    private int create(String str) {
        int result = 0;
        int i = 0;
        boolean lastVowel = checkVowel(str.charAt(0));
        int shift = 1;
        while (i + shift < str.length()) {
            boolean curVowel = checkVowel(str.charAt(i + shift));
            if (curVowel == lastVowel) {
                shift++;
                if (i + shift == str.length()) {
                    result += shift > 2 ? (shift + 1) / 2 - 1 : 0;
                }
            } else {
                lastVowel = !lastVowel;
                result += shift > 2 ? (shift + 1) / 2 - 1 : 0;
                i += shift;
                shift = 1;
            }
        }
        return result;
    }

    private boolean checkVowel(char check) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
        for (char vowel : vowels) {
            if (check == vowel) {
                return true;
            }
        }
        return false;
    }
}
