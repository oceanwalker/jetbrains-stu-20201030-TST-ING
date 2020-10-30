package test.cn.oceanwalker.Strings;

import org.junit.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestGeneratePasswords {
    @Test
    public void test() {
        int uppercaseLetters = 3;
        int lowercaseLetters = 3;
        int digits = 3;
        int symbols = 10;
        uppercaseLetters = 0;
        lowercaseLetters = 0;
        digits = 0;
        symbols = 1;
        for (int i = 0; i < 100; i++) {
//            String pass = generate(uppercaseLetters, lowercaseLetters, digits, symbols);
            String pass = generate1(uppercaseLetters, lowercaseLetters, digits, symbols);
            System.out.println(pass);
            final int countUppercase = countByPattern(pass, "[A-Z]");
            assertEquals(true, countUppercase >= uppercaseLetters);
            final int countLowercase = countByPattern(pass, "[a-z]");
            assertEquals(true, countLowercase >= lowercaseLetters);
            final int countDigit = countByPattern(pass, "[0-9]");
            assertEquals(true, countDigit >= digits);
            assertEquals(true, pass.length() == symbols);
            assertEquals(true, countDigit + countLowercase + countUppercase == symbols);
            assertEquals(false, detectContiansSame(pass));
        }
    }

    private String generate1(int uppercaseLetters, int lowercaseLetters, int digits, int symbols) {

        StringBuilder result = new StringBuilder();
        boolean[] isFirst = new boolean[3];
        int count = 0;
        while (count < symbols) {
            if (uppercaseLetters > 0) {
                result.append(isFirst[0] ? "A" : "B");
                isFirst[0] = !isFirst[0];
                uppercaseLetters--;
            } else if (lowercaseLetters > 0) {
                result.append(isFirst[1] ? "a" : "b");
                isFirst[1] = !isFirst[1];
                lowercaseLetters--;
            } else if (digits > 0) {
                result.append(isFirst[2] ? "0" : "1");
                isFirst[2] = !isFirst[2];
                digits--;
            } else {
                result.append(isFirst[0] ? "A" : "B");
                isFirst[0] = !isFirst[0];
            }
            count++;
        }
        return result.toString();
    }

    private boolean detectContiansSame(String pass) {
        char last = pass.charAt(0);
        for (int i = 1; i < pass.length(); i++) {
            if (pass.charAt(i) == last) {
                return true;
            }
            last = pass.charAt(i);
        }
        return false;
    }

    private int countByPattern(String pass, String p) {
        Pattern pattern = Pattern.compile(p);
        Matcher mather = pattern.matcher(pass);
        StringBuffer buffer = new StringBuffer();
        while (mather.find()) {
            buffer.append(mather.group(0));
        }
        return buffer.length();
    }

    private String generate(int uppercaseLetters, int lowercaseLetters, int digits, int symbols) {
        StringBuilder result = new StringBuilder();
        final int TYPE = 3;
        int[] typeCount = new int[TYPE];
        int[] demandCount = new int[]{uppercaseLetters, lowercaseLetters, digits};
        int count = 0;
        Random random = new Random();
        char last = 0;
        boolean isFull = false;
        boolean isDemandNull = uppercaseLetters == 0 && lowercaseLetters == 0 && digits == 0;
        while (count < symbols) {
            char c = 0;
            int type = random.nextInt(TYPE);
            if (!isFull && typeCount[type] == demandCount[type] && !isDemandNull) {
                continue;
            }
            if (type == 0) {
                c = (char) ('A' + random.nextInt(26));
            } else if (type == 1) {
                c = (char) ('a' + random.nextInt(26));
            } else if (type == 2) {
                c = (char) ('0' + random.nextInt(10));
            }
            if (c == last) {
                continue;
            }
            result.append(c);
            last = c;
            count++;
            typeCount[type]++;

            boolean temp = true;
            for (int i = 0; i < TYPE && !isFull; i++) {
                if (typeCount[i] < demandCount[i]) {
                    temp = false;
                    break;
                }
            }
            isFull = temp;
        }
        return result.toString();
    }
}
